package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.entities.User;
import java.util.Set;


public interface UserDao extends GenericDao<User> {
    User getByLogin(String login);
    
    /**
     * Method finds all Users by rights.
     * @param rights name of right.
     * @return set of users.
     */
    Set<User> findByRights(String rights);
}
