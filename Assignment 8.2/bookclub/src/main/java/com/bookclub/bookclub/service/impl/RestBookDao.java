package com.bookclub.bookclub.service.impl;

import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.service.dao.*;

import java.util.ArrayList;
import java.util.List;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RestBookDao implements BookDao
{
    public RestBookDao()
    {
    }

    public String getBooksDoc(String isbnString)
    {
        String openLibraryUrl= "https://openlibrary.org/api/books";
        RestTemplate rest =new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        System.out.println("getBooksDoc :");
        UriComponentsBuilder builder =UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
          .queryParam("bibkeys",isbnString)
          .queryParam("format", "json")
          .queryParam("jscmd","details");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.toUriString());
        HttpEntity<String> response = rest.exchange(builder.toUriString(),HttpMethod.GET,entity,String.class);
        String jsonBooklist = response.getBody();
        System.out.println("response : " + jsonBooklist);

        return JsonPath.using(Configuration.defaultConfiguration()).parse(jsonBooklist).jsonString();
    }
 
    public List<Book> list()
    {
      String isbnString ="9780593099322,9780261102361,9780261102378,9780590302715,9780316769532";
      System.out.println("list books :" + isbnString);
      String doc = getBooksDoc(isbnString);
      List<Book> books= new ArrayList<Book>();

      List<String> titles = JsonPath.read(doc, "$..title");
      List<String> isbns = JsonPath.read(doc,"$..bib_key");
      List<String>infoUrls=JsonPath.read(doc, "$..info_url");
      System.out.println("titles:" + titles.size() + " : " + isbns.size() + " : " + infoUrls.size());
      for(int index=0;index<titles.size(); index++)
      {
        books.add(new Book(isbns.get(index), titles.get(index), "", infoUrls.get(index), 0));
      }

      return books;

    }

    //override the find method
    public Book find(String key)
    {
      System.out.println("find :" + key);
      String doc= getBooksDoc(key);

      List<String> titles = JsonPath.read(doc, "$..title");
      List<String> isbns = JsonPath.read(doc,"$..bib_key");
      List<String>infoUrls = JsonPath.read(doc, "$..info_url");
      List<String>subtitle = JsonPath.read(doc, "$..details.description");
      List<Integer>pages =JsonPath.read(doc, "$..details.number_of_pages");

      String isbn = isbns.size() >0 ? isbns.get(0): "N/A";
      String title = titles.size() >0 ? titles.get(0): "N/A";
      String desc = subtitle.size()>0 ? subtitle.get(0): "N/A";
      String infoUrl= infoUrls.size()>0 ? infoUrls.get(0): "N/A";
      int numOfPages = pages.size()>0 ? pages.get(0): 0;

      Book book = new Book(isbn, title, desc, infoUrl, numOfPages);
      return book;
    }
}
