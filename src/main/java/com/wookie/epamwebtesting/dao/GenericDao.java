package com.wookie.epamwebtesting.dao;

import java.util.List;
import java.util.Set;


interface GenericDao<E> {
    E create(E e);
    boolean update(E e); //The realization is not actually OK.
    boolean delete(int id); //The realization is not actually OK.
    E findById(int id);
    Set<E> findAll();
}
