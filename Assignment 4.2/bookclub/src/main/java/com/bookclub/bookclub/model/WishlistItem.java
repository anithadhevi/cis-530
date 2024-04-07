package com.bookclub.bookclub.model;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;


public class WishlistItem
{
    @NotNull
    @NotEmpty(message="ISBN is a required field.")
    private String isbn;

    @NotNull
    @NotEmpty(message= "Title is a required field.")
    private String title;

    // Default constructor
    public WishlistItem()
    {

    }
    
    // parameterized constructor
    public WishlistItem(String isbn, String title)
    {
        this.isbn = isbn;
        this.title = title;
    }

    //Getter for isbn
    public String getIsbn()
    {
        return isbn;
    }
   
    //Setter for isbn
    public void setIsbn(String isbn)
    {
        this.isbn=isbn;
    }

    //Getter for title
    public String getTitle()
    {
        return title;
    }
    //Setter for title
    public void setTitle(String title)
    {
        this.title=title;
    }


//Getter and Setter methods for isbn and title

//override toString method
@Override
public String toString()
{
     return "WishlistItem("+ "isbn=' " + isbn +'\''+", title=' " +title +'\''+'}';
}
}


