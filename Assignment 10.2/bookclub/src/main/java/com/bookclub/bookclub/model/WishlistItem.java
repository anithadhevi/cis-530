package com.bookclub.bookclub.model;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="wishlist")
public class WishlistItem
{
    @NotNull
    @NotEmpty(message="ISBN is a required field.")
    private String isbn;

    @NotNull
    @NotEmpty(message= "Title is a required field.")
    private String title;

    @Id
    private String id;

    private String username;

    // Default constructor
    public WishlistItem()
    {

    }
    
    // parameterized constructor
    public WishlistItem(String isbn, String title, String username)
    {
        this.isbn = isbn;
        this.title = title;
        this.username=username;
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
    return id;
   }

   public void setId(String id)
   {
    this.id = id;
   }

   //Getter for username
   public String getUsername()
   {
    return username;
   }

   //setter for username
   public void setUsername(String username)
   {
    this.username=username;
   }

//override toString method
@Override
public String toString()
{
    return "WishlistItem{"+ "Id=' " + id +'\''+", isbn= ' " + isbn + '\''+", title= ' "+title +'\''+", username= ' "+username +'\''+'}';
}
}


