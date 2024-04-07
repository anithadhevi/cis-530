package com.bookclub.bookclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bookclub.bookclub.service.dao.WishlistDao;
import com.bookclub.bookclub.model.*;

public class MemWishlistDao implements WishlistDao 
{
    private List<WishlistItem> wishlist;

    public MemWishlistDao()
    {
        //Intialize teh wishlist array
        wishlist = new ArrayList<>();
       
        //Add some sample wishlist items
        wishlist.add(new WishlistItem("1234567890", "The Hobbit or There and back again"));
        wishlist.add(new WishlistItem("0987654321",  "The fellowship of the ring"));
        wishlist.add(new WishlistItem("235415368",  "The two towers"));
        wishlist.add(new WishlistItem("64597132",  "The return of the kind"));
    }
    @Override
    public List<WishlistItem> list()
    {
        return wishlist;
    }

    @Override
    public WishlistItem find(String isbn)
    {
        for (WishlistItem item: wishlist)
        {
            if(item.getIsbn().equals(isbn))
            {
                return item;//Found matching item
            }
        }
        return null;// Item not found
    }

}

