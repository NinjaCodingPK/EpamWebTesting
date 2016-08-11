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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTestsByTutor implements Command {
    TestService testService = TestService.getInstance();

    /**
     * Command class. Handles studentpage.jsp page. 
     * Method calls test services for getting all tests by tutor.
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
        Integer tutorId = Integer.parseInt(request.getParameter(Constants.ATTRIBUTE_TUTOR));
        Map result = testService.fillTests(testService.getByUser(tutorId));
        request.setAttribute(Constants.PROPERTY_TEST_LIST, result);
        //request.setAttribute("subject", subject);
        return Constants.SHOW_ALL_TESTS_PAGE;
    }
}
