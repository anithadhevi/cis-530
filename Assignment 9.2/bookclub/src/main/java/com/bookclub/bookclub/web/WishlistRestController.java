package com.bookclub.bookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookclub.bookclub.model.WishlistItem;
import com.bookclub.bookclub.service.dao.WishlistDao;
import com.bookclub.bookclub.service.impl.MongoWishlistDao;

@RestController
@RequestMapping(path="/api/wishlist", produces="application/json") 
@CrossOrigin(origins="*")
public class WishlistRestController 
{

private WishlistDao wishlistDao;

public WishlistRestController()
{
    //INstainate a new Mango WishListDao object
    this.wishlistDao = new MongoWishlistDao();
}

@Autowired
public void setWishlistDao(WishlistDao wishlistDao)
{
    this.wishlistDao=wishlistDao;
}

@GetMapping("/hello")
public String hello()
{
    return "Hello from WishListController!";
} 

@RequestMapping(path={"","/",},method=RequestMethod.GET)
public List<WishlistItem> showWishlist(Authentication authentication)
{
    return wishlistDao.list(authentication.getName());
}

@RequestMapping(path="/find/{id}",method=RequestMethod.GET)
public WishlistItem findById(@PathVariable String id)
{
    return wishlistDao.find(id);
}
}