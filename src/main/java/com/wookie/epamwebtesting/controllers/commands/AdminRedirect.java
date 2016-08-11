/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.services.SubjectService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminRedirect implements Command {
    SubjectService subjectService = SubjectService.getInstance();

    /**
     * Command class. Handles adminpage.jsp page. 
     * Method calls test and subject service for filling necessary data for administrator's
     * page from database.
     * @param request
     * @param response
     * @return name of administrator's page from Constants class.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, RuntimeException {

        Set<Subject> subjects = subjectService.getSubjects();
        request.setAttribute(Constants.ATTRIBUTE_SUBJECTS, subjects);

        return Constants.ADMIN_PAGE;
    }

}
