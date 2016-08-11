/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ShowProfile implements Command {

    /**
     * Command class. Handles studentpage.jsp, adminpage.jsp and tutorpage.jsp pages. 
     * Method uses session for getting user's data and sends it into profile page.
     * @param request
     * @param response
     * @return name of page for forwarding.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        HttpSession session = request.getSession();
        
        request.setAttribute(Constants.ATTRIBUTE_USER, (User)session.getAttribute(Constants.USER_SESSION_ATTRIBUTE));
        
        return Constants.PROFILE_PAGE;
    }
    
}
