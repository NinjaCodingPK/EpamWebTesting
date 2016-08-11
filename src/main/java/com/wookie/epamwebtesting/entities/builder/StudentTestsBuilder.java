/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.entities.builder;

import com.wookie.epamwebtesting.entities.StudentTests;

/**
 *
 * @author wookie
 */
public class StudentTestsBuilder {
    private StudentTests instance;
    
    public StudentTestsBuilder() {
        instance = new StudentTests();
    }

    public StudentTestsBuilder setStudentId(int studentId) {
        instance.setStudentId(studentId);
        return this;
    }

    public StudentTestsBuilder setTestId(int testId) {
        instance.setTestId(testId);
        return this;
    }

    public StudentTestsBuilder setResult(int result) {
        instance.setResult(result);
        return this;
    }
    
    public StudentTests build() {
        return instance;
    }
}
