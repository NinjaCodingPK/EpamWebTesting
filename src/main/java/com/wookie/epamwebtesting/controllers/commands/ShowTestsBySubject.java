/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.services.TestService;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowTestsBySubject implements Command {
    TestService testService = TestService.getInstance();

    /**
     * Command class. Handles studentpage.jsp page. 
     * Method calls test services for getting all tests by subject.
     * @param request
     * @param response
     * @return name of page for forwarding.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, RuntimeException {

        //String subject = request.getParameter("subject");
        int subject = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_SUBJECT));
        Map result = testService.fillTests(testService.getBySubject(subject));
        request.setAttribute(Constants.PROPERTY_TEST_LIST, result);
        request.setAttribute(Constants.ATTRIBUTE_SUBJECT, subject);
        return Constants.SHOW_ALL_TESTS_PAGE;
    }
}
