/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.services;

import com.wookie.epamwebtesting.dao.DaoFactory;
import com.wookie.epamwebtesting.dao.SubjectDao;
import com.wookie.epamwebtesting.dao.TestDao;
import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
import java.util.Set;


public class SubjectService {
    private SubjectDao subjectDao = DaoFactory.getFactory().createSubjectDao();
    private TestDao testDao = DaoFactory.getFactory().createTestDao();
    private TestService testService = TestService.getInstance();
    
    private static SubjectService instance = new SubjectService();

    public static SubjectService getInstance() throws RuntimeException {
        return instance;
    }

    /**
     * Method finds all subjects.
     *
     * @return set of subjects.
     */
    public Set<Subject> getSubjects() throws RuntimeException {
        return subjectDao.findAll();
    }

    /**
     * Method deletes subject and all subject's test.
     * @param subjectId ID of subject.
     * @throws RuntimeException 
     */
    public void deleteSubject(int subjectId) throws RuntimeException  {
        Set<Test> tests = testDao.findBySubject(subjectId);
        
        for(Test t : tests) {
            testService.deleteTest(t.getId());
        }
        
        subjectDao.delete(subjectId);
    }

    /**
     * Method add subject in database.
     * @param subjectName
     * @throws RuntimeException 
     */
    public void addSubject(String subjectName) throws RuntimeException  {
            subjectDao.create(new SubjectBuilder().setName(subjectName).build());
    }
}
