package com.bookclub.bookclub.service.dao;
import java.util.List;

import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.service.*;


public interface BookDao extends GenericDao<Book,String> 
{
    List<Book> list(String isbString);
}
