package com.bookclub.bookclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;

import com.bookclub.bookclub.model.WishlistItem;
import com.bookclub.bookclub.service.dao.WishlistDao;
import com.bookclub.bookclub.service.impl.*;

import jakarta.validation.Valid;


@Controller
@RequestMapping ("/wishlist")
public class WishlistController 
{ 
    //private final WishlistService wishlistService;
    @Autowired
    WishlistDao wishlistDao = new MongoWishlistDao();

    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao)
    {
        this.wishlistDao=wishlistDao;
    }

   
    @RequestMapping(path="/show", method = RequestMethod.GET)
    public String showWishlist()
    {
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
    public String addWishlistItem (@Valid @ModelAttribute("wishlistItem") WishlistItem wishlistItem, BindingResult bindingresult, Authentication authentication)
    {
        if(bindingresult.hasErrors())
        {
            return "wishlist/new";//Return to "wishlist/new" page for validation errors
        }
        wishlistItem.setUsername(authentication.getName());
        wishlistDao.add(wishlistItem);//add the record to MongoDB

        return "redirect:/wishlist/show";//Redirect to "/wishlist" after successfully adding them
    }

    @RequestMapping(method= RequestMethod.GET,path ="/{id}")
    public String showWishlistItem(@PathVariable("id") String id, Model model)
    {
        WishlistItem wishlistItem = wishlistDao.find(id);
        model.addAttribute("wishlistItem", wishlistItem);

        return "wishlist/view";
     }

    
    @RequestMapping(method= RequestMethod.POST,path ="/update")
    public String updateWishlistItem(@Valid WishlistItem wishlistItem,BindingResult bindingResult, Authentication authentication)
    {
        wishlistItem.setUsername(authentication.getName());
        
        if(bindingResult.hasErrors())
        {
            return "wishlist/view";
        }
        wishlistDao.update(wishlistItem);

        return "redirect:/wishlist/show";
    }

    @RequestMapping(method=RequestMethod.GET, path="/remove/{id}")
    public String removeWishlistItem(@PathVariable("id") String id, Model model)
    {
        wishlistDao.remove(id);
        return "redirect:/wishlist/show";
    }
}

