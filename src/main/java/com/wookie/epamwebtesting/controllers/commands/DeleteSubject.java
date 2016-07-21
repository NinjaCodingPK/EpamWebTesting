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


public class DeleteSubject implements Command {

    SubjectService subjectService = SubjectService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {

        Integer subjectId = Integer.parseInt(request.getParameter("subjectId"));

        subjectService.deleteSubject(subjectId);
        response.sendRedirect(Constants.REDIRECT_ADMIN_PAGE);
        return null;
    }

}
