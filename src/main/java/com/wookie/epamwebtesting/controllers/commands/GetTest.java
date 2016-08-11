/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.services.TestService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetTest implements Command {
    TestService testService = TestService.getInstance();

    /**
     * Command class. Handles alltests.jsp page. 
     * Method calls test service for getting a proper test from database.
     * @param request
     * @param response
     * @return name of page for forwarding.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        String testId = request.getParameter(Constants.PROPERTY_TEST_ID);
        Test test = testService.getTest(Integer.parseInt(testId));
        
        request.setAttribute(Constants.ATTRIBUTE_TEST, test);
        request.setAttribute(Constants.PROPERTY_TEST_ID, testId);

        return Constants.SHOW_TEST_PAGE;
    }

}
