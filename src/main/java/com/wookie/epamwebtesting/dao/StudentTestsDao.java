/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.entities.StudentTests;
import java.util.Set;

/**
 *
 * @author wookie
 */
public interface StudentTestsDao extends GenericDao<StudentTests> {
    Set<StudentTests> getByStudent(int studentId);
    Integer getResult(int studentId, int testId);
}
