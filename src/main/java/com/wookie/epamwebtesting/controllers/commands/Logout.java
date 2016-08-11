/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Logout implements Command {

    /**
     * Command class. Handles studentpage.jsp, adminpage.jsp and tutorpage.jsp pages. 
     * Method removes users' data from session.
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

        HttpSession session = request.getSession(false);
        
        if(session != null) {
            session.invalidate();
        }
        
        //request.getSession().removeAttribute(Constants.USER_SESSION_ATTRIBUTE);
//        request.getSession().setAttribute(Constants.USER_SESSION_ATTRIBUTE, null);

        return Constants.LOGIN_PAGE;
    }
    
}
