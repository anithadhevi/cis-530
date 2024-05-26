package com.bookclub.bookclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.bookclub.bookclub.model.WishlistItem;
import com.bookclub.bookclub.service.dao.WishlistDao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao
{
    @Autowired
   private MongoTemplate mongoTemplate;
 
   @Override
   public List<WishlistItem> list(String username)
   {
      Query query= new Query();
      query.addCriteria(Criteria.where("username").is(username));
      return mongoTemplate.find(query,WishlistItem.class);
      //return mongoTemplate.findAll(WishlistItem.class);
   }
  
   @Override
   public boolean add(WishlistItem entity)
   {
      try{
         mongoTemplate.save(entity);
         return true;
      }
      catch(Exception e)
      {
         return false;
      }
   }

   @Override
   public boolean remove(String key)   
   {
      Query query = new Query();
      query.addCriteria(Criteria.where("id").is(key));
      mongoTemplate.remove(query,WishlistItem.class);

      return true;
   }

   @Override
   public void update(WishlistItem entity)
   {
      WishlistItem wishlistItem = mongoTemplate.findById(entity.getId(),WishlistItem.class);

      if(wishlistItem != null)
      {
         wishlistItem.setIsbn(entity.getIsbn());
         wishlistItem.setTitle(entity.getTitle());
         wishlistItem.setUsername(entity.getUsername());
         mongoTemplate.save(wishlistItem);
      }
   }

   @Override
   public WishlistItem find(String key)
   {
      Query query = new Query();
      query.addCriteria(Criteria.where("id").is(key));
      WishlistItem findresult = mongoTemplate.findOne(query, WishlistItem.class);

      return findresult;
   }

}

