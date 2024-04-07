package com.bookclub.bookclub.service;

import java.util.List;

public interface GenericDao<E,K>
{
    List<E> list();//return alist of object of type E
    E find(K key);//retun an object of type E by type k value
}