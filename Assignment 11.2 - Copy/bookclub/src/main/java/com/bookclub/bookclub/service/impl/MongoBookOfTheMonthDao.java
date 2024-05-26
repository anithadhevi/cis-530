package com.bookclub.bookclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.model.BookOfTheMonth;
import com.bookclub.bookclub.service.dao.BookOfTheMonthDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("bookOftheMonth")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao
{
    @Autowired
    private MongoTemplate mongoTemplate;

    /*public MongoBookOfTheMonthDao(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate=mongoTemplate;
    }*/

    @Override
    public boolean add(BookOfTheMonth book)
    {
        try
        {
            mongoTemplate.save(book);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean remove(String id)
    {
        Query query =new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query,BookOfTheMonth.class);

        return true;
    }

    @Override
    public List<BookOfTheMonth> list(String key)
    {
        int month=Integer.parseInt(key);

        System.out.println("Month: "+month);

        if(month == 999)
        {
            return mongoTemplate.findAll(BookOfTheMonth.class);
        }

        Query query =new Query();
        query.addCriteria(Criteria.where("month").is(month));
        return mongoTemplate.find(query, BookOfTheMonth.class);
    }

    @Override
    public void update(BookOfTheMonth book)
    {
        BookOfTheMonth bookOfTheMonth = mongoTemplate.findById(book.getId(),BookOfTheMonth.class);

        if(bookOfTheMonth != null)
        {
            bookOfTheMonth.setIsbn(book.getIsbn());
            bookOfTheMonth.setMonth(book.getMonth());
            mongoTemplate.save(bookOfTheMonth);
        }
    }

    @Override
    public BookOfTheMonth find(String key)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(key));
        BookOfTheMonth findresult = mongoTemplate.findOne(query, BookOfTheMonth.class);

        return findresult;
    }
}
