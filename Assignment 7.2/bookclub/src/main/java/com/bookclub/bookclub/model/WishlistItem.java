package com.bookclub.bookclub.model;
import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("wishlist")
public class WishlistItem
{
    @NotNull
    @NotEmpty(message="ISBN is a required field.")
    private String isbn;

    @NotNull
    @NotEmpty(message= "Title is a required field.")
    private String title;

    @Id
    private String Id;

    // Default constructor
    public WishlistItem()
    {

    }
    
    // parameterized constructor
    public WishlistItem(String isbn, String title)
    {
        //this.Id = Id;
        this.isbn = isbn;
        this.title = title;
    }
    //Getter and Setter methods for isbn and title

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

   //Getter for Id
   public String getId()
   {
    return Id;
   }

   //Setter for Id
    public void setId(String Id)
    {
        this.Id = Id;
    }

//override toString method
    @Override
    public String toString()
    {
        return "WishlistItem{"+ "Id=' " + Id +'\''+", isbn= ' " + isbn + '\''+", title= ' "+title +'\''+'}';
    }
}


