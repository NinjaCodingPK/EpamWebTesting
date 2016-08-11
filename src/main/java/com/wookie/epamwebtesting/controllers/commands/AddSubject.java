/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.services.SubjectService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddSubject implements Command {
    SubjectService subjectService = SubjectService.getInstance();
    
    /**
    * Command class. Handles adminpage.jsp. 
    * Method call subject's service to add subject in database. 
    * @param request
    * @param response
    * @return null because of using a redirection.
    * @throws ServletException
    * @throws IOException
    * @throws RuntimeException if some mistake in model arises. 
    */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        String subjectName = request.getParameter(Constants.PROPERTY_SUBJECT_NAME);

        subjectService.addSubject(subjectName);

        response.sendRedirect(Constants.REDIRECT_ADMIN_PAGE);
        return null;
    }

}
