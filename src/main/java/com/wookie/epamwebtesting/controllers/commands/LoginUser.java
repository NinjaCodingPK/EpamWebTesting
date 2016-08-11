/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.User;
import com.wookie.epamwebtesting.services.TestService;
import com.wookie.epamwebtesting.services.UserService;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginUser implements Command {
    private UserService userService = UserService.getInstance();
    
    /**
     * Command class. Handles index.jsp page. 
     * Method use HttpSession for handling user's data.
     * @param request
     * @param response
     * @return name of page for forwarding.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if login data is wrong. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, RuntimeException {
        try {
            User user = userService.getUser(request.getParameter(Constants.PROPERTY_USER_LOGIN));
            
            userService.checkPassword(user, request.getParameter(Constants.PROPERTY_USER_PASSWORD));
            
            HttpSession session = request.getSession();
            session.setAttribute(Constants.USER_SESSION_ATTRIBUTE, user);
            
            return userService.processUser(request, user);
        } catch (Exception ex) { //Wrong password.
            throw new RuntimeException(ex);
            //return Constants.LOGIN_PAGE;
        }
    }
    
}
