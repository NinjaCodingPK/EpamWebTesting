package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.dao.jdbc.JdbcDaoFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;


interface GenericDao<E> {
    E create(E e);
    boolean update(E e);
    boolean delete(int id);
    E findById(int id);
    Set<E> findAll();
}
