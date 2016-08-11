/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.User;
import com.wookie.epamwebtesting.services.TestService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteTest implements Command {
    TestService testService = TestService.getInstance();

    /**
     * Command class. Handles addtest.jsp page. 
     * Method calls task service for deleting test from database.
     * @param request
     * @param response
     * @return name of tutor's page from Constants class.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {

        Integer testId = Integer.parseInt(request.getParameter(Constants.PROPERTY_TEST_ID));

        testService.deleteTest(testId);
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION_ATTRIBUTE);

        Map tests = testService.fillTests(testService.getByUser(user.getId()));
        request.setAttribute(Constants.PROPERTY_RESULT_LIST, tests);

        return Constants.TUTOR_PAGE;
    }

}
