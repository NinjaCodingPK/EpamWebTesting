package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.entities.Subject;
import java.util.Set;


public interface SubjectDao extends GenericDao<Subject> {
     /**
     * Method finds subject's ID by name; 
     * @param subject name of subject.
     * @return subject's ID.
     */
     public Integer getId(String subject);
}
