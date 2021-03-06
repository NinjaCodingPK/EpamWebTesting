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
import javax.servlet.http.HttpSession;


public class ShowTutorsTests implements Command{
    private TestService testService = TestService.getInstance();
    
    /**
     * Command class. Handles tutorpage.jsp page. 
     * Method uses session for getting all tests by current user info.
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
        HttpSession session = request.getSession();
        User currentuser = (User)session.getAttribute(Constants.USER_SESSION_ATTRIBUTE);
        Map result = testService.fillTests(testService.getByUser(currentuser.getId()));
        //Map result = testService.fillTests(testService.getByUser(tutorId));
        request.setAttribute(Constants.PROPERTY_TEST_LIST, result);
        return Constants.TUTOR_PAGE;
    }
    
}
