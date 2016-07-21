package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.entities.Test;
import java.util.Set;


public interface TestDao extends GenericDao<Test> {
    /**
     * Method finds all entries in database by subject.
     * @param subjectId id of subject.
     * @return list of matched entries.
     */
    Set<Test> findBySubject(int subjectId);
    
    /**
     * Method finds all entries in database by user.
     * @param userId id of subject.
     * @return list of matched entries.
     */
    Set<Test> findByUser(int userId);
}
