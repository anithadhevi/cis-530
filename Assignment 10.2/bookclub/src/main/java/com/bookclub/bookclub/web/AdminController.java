package com.bookclub.bookclub.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bookclub.bookclub.model.BookOfTheMonth;
import com.bookclub.bookclub.service.dao.BookOfTheMonthDao;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/monthly-books")
public class AdminController 
{
    private final BookOfTheMonthDao bookOfTheMonthDao;

    @Autowired
    public AdminController(BookOfTheMonthDao bookOfTheMonthDao)
    {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    @RequestMapping(value = {"/showBookOfTheMonth", "/", ""}, method = RequestMethod.GET)
    public String showBookOfTheMonth(Model model)
    {
        System.out.println("Showbookof the month");
        List<BookOfTheMonth> booksOfTheMonth = bookOfTheMonthDao.list("999");
        model.addAttribute("booksOfTheMonth", booksOfTheMonth);

        return "monthly-books/list";
    }

    public Map<Integer, String> getMonths()
    {
        Map<Integer, String> months= new HashMap<>();
        months.put(1, "January");
        months.put(2,"Feburary");
        months.put(3,"March");
        months.put(4,"April");
        months.put(5,"May");
        months.put(6,"June");
        months.put(7,"July");
        months.put(8,"August");
        months.put(9,"September");
        months.put(10,"October");
        months.put(11,"November");
        months.put(12,"December");
        
        return months;
    }

    public String bookOfTheMonthForm(Model model)
    {
        Map<Integer, String> months = getMonths();
        model.addAttribute("months", months);
    
        //Add a new BookOfTheMonth Object
        BookOfTheMonth bookOfTheMonth = new BookOfTheMonth();
        model.addAttribute("bookOfTheMonth",bookOfTheMonth);
        return  "admin/bookOfTheMonthForm";
    }

    @RequestMapping(method = RequestMethod.GET, path="/new")
    public String ShowmonthlyBooks(Model model)
     {
        model.addAttribute("bookOfTheMonth", new BookOfTheMonth());
        model.addAttribute("monthData", getMonths());

        //Return the HTML page located at "wishlist/new"
        return "monthly-books/new";//Assuming "new.html" is located is the "wishlist" folder
    }

    @PostMapping("/addBookOfTheMonth")
    public String addBookOfTheMonth(@Valid @ModelAttribute("bookOfTheMonth") BookOfTheMonth bookOfTheMonth,
                                    BindingResult bindingResult, Model model) 
    {
        if (bindingResult.hasErrors()) 
        {
            return "/monthly-books/bookOfTheMonthForm";
        }

        bookOfTheMonthDao.add(bookOfTheMonth);
        return "redirect:/monthly-books/";
    }

    @GetMapping("/removeBookOfTheMonth/{id}")
    public String removeBookOfTheMonth(@PathVariable String id) 
    {
        bookOfTheMonthDao.remove(id);
        return "redirect:/monthly-books/";
    }
}

