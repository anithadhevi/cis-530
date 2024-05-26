package com.bookclub.bookclub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import com.bookclub.bookclub.model.Book;
import com.bookclub.bookclub.model.BookOfTheMonth;
import com.bookclub.bookclub.service.impl.MongoBookOfTheMonthDao;
import com.mongodb.client.result.DeleteResult;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest
public class SpringBootBookclubTests 
{
    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private MongoBookOfTheMonthDao mongoBookOfTheMonthDao;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    
    @Test//Web unit test to findBooksByID
    public void findBookOfTheMonth()
    {
        BookOfTheMonth bookMonth = new BookOfTheMonth("122",5,"ISBN12345");

        when(mongoTemplate.findOne(any(Query.class), any(Class.class))).thenReturn(bookMonth);
        BookOfTheMonth result = mongoBookOfTheMonthDao.find("122");
            
        assertEquals("122", result.getId());
    }

    @Test//web unit test to showBookOfTheMonth
    public void showBookOfTheMonth()
    {
        List<BookOfTheMonth> bookMonth = new ArrayList<>();
        bookMonth.add(new BookOfTheMonth("125", 1, "java programming"));
        bookMonth.add(new BookOfTheMonth("126", 2, "C++ programming"));
 
        lenient().when(mongoTemplate.findAll(BookOfTheMonth.class)).thenReturn(bookMonth);
        assertTrue(bookMonth.size() > 0);
    }

    @Test//Java unit Test to remove 
    public void removeBookOfTheMonth() throws Exception
    {
         String keytoRemove = "125";
        assertEquals(mongoBookOfTheMonthDao.remove(keytoRemove), true);
     }
        
     @Test
      public void saveBookofTheMonth() throws Exception
      {
        String keyToSave="122";
        BookOfTheMonth book =  new BookOfTheMonth("125", 1, "java programming");
        assertEquals(mongoBookOfTheMonthDao.add(book), true);
      }
      
     }

