package com.bookclub.bookclub.web;
//import com.bookclub.bookclub.controller;
import com.bookclub.bookclub.model.Book;
//import com.bookclub.bookclub.service.dao.BookDao;
//import com.bookclub.bookclub.service.impl.MemBookDao;
import com.bookclub.bookclub.service.impl.RestBookDao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController 
{
   @RequestMapping(value = { "/","/index"}, method= {RequestMethod.POST, RequestMethod.GET} )
    public String showHome(Model model)
    {
        RestBookDao bookDao = new RestBookDao();//create an instance of MemBookDao
        List<Book>books = bookDao.list();//get the list of books from MemBookDao
       
        model.addAttribute("books", books);// add books to the model with the key"books"
        
        return "index";//Return the name of the Thymeleaf template(e.g home.html)
    }

    @RequestMapping(value= "/{id}", method=RequestMethod.GET)
    public String getMonthlyBook(@PathVariable("id") String id, Model model)
    {
        String isbn=id;
        
        RestBookDao bookDao = new RestBookDao();
        Book book = bookDao.find(isbn); //Find the books by ID using MemoBNookDao
        
        model.addAttribute("book", book);//Add the book to the model with the key "book"
        return "monthly-books/view";//return the Thymeleaf template "monthly-books/view.html"
    }
    

    @RequestMapping(method = RequestMethod.GET, path="/about")
    public String showAboutUs(Model model)
    {
        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, path="/contact")
    public String showContactUs(Model Model)
    {
        return "contact";
    }
}
