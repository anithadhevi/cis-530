package com.bookclub.bookclub.service;
import java.util.List;


public interface GenericCrudDao<E,K>
{
    E find (K key);
    List<E> list();
    void update (E entity);
    boolean remove (E entity);
    void add(E entity);
}
