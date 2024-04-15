package com.bookclub.bookclub.service.impl;

import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.service.dao.*;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;


public class MemBookDao implements BookDao
{
  private List<Book> books;

  public MemBookDao()
  {
      //intialize the list of books and the five books object
      this.books=new ArrayList<>();
      books.add(new Book("ISBN1", "Book 1", "Description 1", 200, List.of("Author 1")));
      books.add(new Book("ISBN2", "Book 2", "Description 2", 300, List.of("Author 2")));
      books.add(new Book("ISBN3", "Book 3", "Description 3", 400, List.of("Authors 3")));
      books.add(new Book("ISBN4", "Book 4", "Description 4", 500, List.of("Authors 4")));
      books.add(new Book("ISBN5", "Book 5", "Description 5", 600, List.of("Authors 5")));
  }

 //Override the books 
  public List<Book> list()
  {
    return this.books;
  }

  //override the find method
  public Book find(String key)
  {
    for (Book book : this.books)
    {
        if(book.getIsbn().equals(key))
        {
            return book;
        }
        
    }
    return new Book();
  }
}
