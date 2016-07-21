/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.services.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Don't need registration at all...
 * @author wookie
 */
public class BookUser implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UserService userService = new UserService();
//        userService.createUser((String)request.getParameter(Constants.USER_NAME),
//                                (String)request.getParameter(Constants.USER_SURNAME),
//                                (String)request.getParameter(Constants.USER_LOGIN),
//                                (String)request.getParameter(Constants.USER_PASSWORD),
//                                (String)request.getParameter(Constants.USER_RIGHTS));
        
        return Constants.LOGIN_PAGE;
    }
    
}
