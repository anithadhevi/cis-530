package com.bookclub.bookclub.web;
//import com.bookclub.bookclub.controller;
import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.model.BookOfTheMonth;
import com.bookclub.bookclub.service.dao.BookDao;
import com.bookclub.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.bookclub.service.impl.MongoBookOfTheMonthDao;
//import com.bookclub.bookclub.service.impl.MemBookDao;
import com.bookclub.bookclub.service.impl.RestBookDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController
{
    private BookOfTheMonthDao bookOfTheMonthDao;

    @Autowired
    public HomeController(BookOfTheMonthDao bookOfTheMonthDao)
    {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao)
    {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

   @RequestMapping(value = { "/","/index"}, method= {RequestMethod.POST, RequestMethod.GET} )
    public String showHome(Model model)
    {
        //BookOfTheMonthDao mongoBookOfTheMonthDao = new MongoBookOfTheMonthDao();
        //List<BookOfTheMonth> bookOfTheMonth = mongoBookOfTheMonthDao.list();
        //model.addAttribute("booksOfTheMonth", bookOfTheMonth);
        //return "home";
        Date date = new Date();
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        int calMonth = cal.get(Calendar.MONTH)+1;
        //calMonth = 999;
        List<BookOfTheMonth> monthlyBooks = bookOfTheMonthDao.list(Integer.toString(calMonth));

        StringBuilder isbnBuilder = new StringBuilder();
        isbnBuilder.append("ISBN: ");
        for(BookOfTheMonth monthlyBook : monthlyBooks)
        {
            isbnBuilder.append(monthlyBook.getIsbn()).append(",");
        }
        
        String isbString =isbnBuilder.toString().substring(0,isbnBuilder.toString().length()-1);
        System.out.println("show home : " + isbString);
        RestBookDao bookDao = new RestBookDao();
        List<Book> bookOfTheMonth = bookDao.list(isbString); 
        model.addAttribute("books", bookOfTheMonth);

        return "index";
    }


        /*RestBookDao bookDao = new RestBookDao();//create an instance of MemBookDao
        List<Book>books = bookDao.list();//get the list of books from MemBookDao
       model.addAttribute("books", books);// add books to the model with the key"books"
         return "index";//Return the name of the Thymeleaf template(e.g home.html)
    }*/

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

