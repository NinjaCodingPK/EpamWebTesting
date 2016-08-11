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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wookie
 */
public class ShowStatistic implements Command {
    private TestService testService = TestService.getInstance();
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, RuntimeException {
        
        User user = (User)request.getSession().getAttribute(Constants.USER_SESSION_ATTRIBUTE);
        
        request.setAttribute(Constants.PROPERTY_TEST_LIST, testService.getStudentTests(user.getId()));
        //request.setAttribute(Constants.PROPERTY_TEST_LIST, testService.fillTests(testService.getByUser(user.getId())));
                
        return Constants.STATISTIC_PAGE;
    }
    
}
