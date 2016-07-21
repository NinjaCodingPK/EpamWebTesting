/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.entities.User;
import com.wookie.epamwebtesting.services.SubjectService;
import com.wookie.epamwebtesting.services.TestService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddTest implements Command {

    TestService testService = TestService.getInstance();
    SubjectService subjectService = SubjectService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {

        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION_ATTRIBUTE);
        Subject subject = (Subject) subjectService.getSubjects().toArray()[0];

        Integer tutorId = user.getId();

        Integer testId = testService.addTest(tutorId, subject.getId());
        response.sendRedirect(Constants.REDIRECT_UPDATE_PAGE + testId);
        return null;
    }

}
