package com.bookclub.bookclub.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import com.bookclub.bookclub.model.WishlistItem;
import com.bookclub.bookclub.service.dao.WishlistDao;
import com.bookclub.bookclub.service.impl.*;

import jakarta.validation.Valid;


@Controller
@RequestMapping ("/wishlist")
public class WishlistController 
{ 
    //private WishlistDao wishlistDao;
    WishlistDao wishlistDao = new MongoWishlistDao();
    
    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao)
    {
        this.wishlistDao=wishlistDao;
    }

    @RequestMapping(method = RequestMethod.GET, path="/show")
    public String showWishlist(Model model) 
    {
        System.out.println("start show");
        //create a new instance of MemWishlistDao and bind the results to thw wishlist variable
        //MemWishlistDao wishlistDao = new wishlistDao();
      
        //call the list() method from MemwishlistDaomand biond the reults to the wishlist variable;
        List<WishlistItem> wishlist = wishlistDao.list();

        //Add the wishlist variable as a model attribute with the key "wishlist"
        model.addAttribute("wishlist", wishlist);

        //Retun the HTML page located at "wishlist/list"
         return "wishlist/list";//Assuming "list.html" is located in the "wishlist" folder
    }

    @RequestMapping(method = RequestMethod.GET, path="/new")
    public String wishlistForm(Model model)
     {
        //create a new WishlistItem object and add its as a model attribute with the key "wishlistItem"
        model.addAttribute("wishlistItem", new WishlistItem());

        //Return the HTML page located at "wishlist/new"
        return "wishlist/new";//Assuming "new.html" is located is the "wishlist" folder
    }

    @PostMapping("/submit")
    public String addWishlistItem (@Valid @ModelAttribute("wishlistItem") WishlistItem wishlistItem, BindingResult bindingresult)
    {
        System.out.println(wishlistItem.toString());
        System.out.println(bindingresult.getAllErrors());
        if(bindingresult.hasErrors())
        {
            return "wishlist/new";//Return to "wishlist/new" page for validation errors
        }
        
         wishlistDao.add(wishlistItem);//add the record to MongoDB
         return "redirect:/wishlist/show";//Redirect to "/wishlist" after successfully adding them
        
    }

    
}

