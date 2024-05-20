package com.bookclub.bookclub.service.dao;
import com.bookclub.bookclub.service.*;

import java.util.List;

import com.bookclub.bookclub.model.WishlistItem;

public interface WishlistDao extends GenericCrudDao<WishlistItem,String>
{
    List<WishlistItem> list(String username);
}

    


