/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.services.SubjectService;
import com.wookie.epamwebtesting.services.TestService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ToUpdateTest implements Command {

    TestService testService = TestService.getInstance();
    SubjectService subjectService = SubjectService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
     
        Integer id = Integer.parseInt(request.getParameter("testId"));
        Test test = testService.getTest(id);
        Set<Subject> subjects = subjectService.getSubjects();

        request.setAttribute("test", test);
        request.setAttribute("subjects", subjects);

        return Constants.ADD_TEST_PAGE;
    }

}
