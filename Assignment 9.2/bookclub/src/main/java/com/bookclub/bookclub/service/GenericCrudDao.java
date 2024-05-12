package com.bookclub.bookclub.service;
import java.util.List;


public interface GenericCrudDao<E,K>
{
    void add(E entity);
    void update(E entity);
    E find (K key);
    List<E> list(K key);
    boolean remove (K key);
}
