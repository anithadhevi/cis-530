package com.bookclub.bookclub.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;

@Document(collection="bookOftheMonth")
public class BookOfTheMonth 
{
    @Id
    private String id;
    private Integer month;

    @NotEmpty(message= "ISBN is required")
    private String isbn;

    //Constructor
    public BookOfTheMonth(String id,Integer month, String isbn)
    {
        this.id= id;
        this.month=month;
        this.isbn=isbn;
    }

    //Default constructor
    public BookOfTheMonth()
    {

    }

    //Getter and Setter Methods
    public String getId()
    {
        return id;
    }
     public void setId(String id)
     {
        this.id=id;
     }

    public Integer getMonth()
    {
        return month;
    }

    public void setMonth(Integer month)
    {
        this.month=month;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn=isbn;
    }

    //Override toString() method
    @Override
    public String toString()
    {
        return "Book{" + "id=' "+id+ '\'' +",month=" +month+",isbn='"+isbn+'\''+'}';

    }
}
