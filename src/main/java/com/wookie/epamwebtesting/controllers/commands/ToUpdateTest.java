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

    /**
     * Command class. Handles tutorpage.jsp page. 
     * Method forms a proper data for sending it into addtest jsp page.
     * @param request
     * @param response
     * @return name of page for forwarding.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, RuntimeException {
     
        Integer id = Integer.parseInt(request.getParameter(Constants.PROPERTY_TEST_ID));
        Test test = testService.getTest(id);
        Set<Subject> subjects = subjectService.getSubjects();

        request.setAttribute(Constants.ATTRIBUTE_TEST, test);
        request.setAttribute(Constants.ATTRIBUTE_SUBJECTS, subjects);

        return Constants.ADD_TEST_PAGE;
    }

}
