package com.bookclub.bookclub.model;

import java.util.List;

public class Book
{
    private String isbn;
    private String title;
    private String description;
    private int numOfPages;
    private List<String> authors;

    public Book()
    {}

    public Book(String isbn, String title, String description, int numOfPages, List<String>authors)
    {
        this.isbn=isbn;
        this.title=title;
        this.description=description;
        this.numOfPages=numOfPages;
        this.authors=authors;
    }
    //Getter and Setter for the private field
    public String getIsbn()
    {
        return isbn;
    }
    public void setIsbn(String isbn)
    {
        this.isbn=isbn;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public String getDescription()
    {   
        return description;
    }
    public void setDescription(String description)
    {
        this.description=description;
    }
    public int getNumOfPages()
    {
        return numOfPages;
    }
    public void setNumOfPages(int numOfPages)
    {
        this.numOfPages = numOfPages;
    }
    public List<String> getAuthors()
    {
        return authors;
    }
    public void setAuthors(List<String> author)
    {
        this.authors = author;
        
    }
//toString method   to provide a string presentation of the Book Object
 
//@Override
    public String  toString()
    {
        return "Book{"+
        "isbn=' " + isbn + '\''  +
        ",title=' " + title + '\'' +
        ",description=' " + description +'\'' +
        ", numOfPages=" + numOfPages +
        ", authors=" + authors +
        '}';
    }                
}

