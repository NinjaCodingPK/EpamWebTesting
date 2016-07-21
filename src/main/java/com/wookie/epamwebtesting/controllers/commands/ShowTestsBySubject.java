/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.services.TestService;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowTestsBySubject implements Command {

    TestService testService = TestService.getInstance();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {

        //String subject = request.getParameter("subject");
        int subject = Integer.parseInt(request.getParameter("subject"));
        Map result = testService.fillTests(testService.getBySubject(subject));
        request.setAttribute("testlist", result);
        request.setAttribute("subject", subject);
        return Constants.SHOW_ALL_TESTS_PAGE;
    }
}
