package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.entities.Task;



public interface TaskDao extends GenericDao<Task> {
    Task getByText(String text); 
}
