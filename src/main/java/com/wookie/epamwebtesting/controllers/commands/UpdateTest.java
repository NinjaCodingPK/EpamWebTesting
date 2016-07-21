/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.services.TestService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateTest implements Command {

    private TestService testService = TestService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        String subject = request.getParameter("subj");
        int id = Integer.parseInt(request.getParameter("id"));
        testService.setSubject(id, subject);

        response.sendRedirect(Constants.REDIRECT_UPDATE_PAGE + id);
        return null;/*Constants.REDIRECT_UPDATE_PAGE + id;*/
    }

}