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
import com.wookie.epamwebtesting.services.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserRedirect implements Command {
    UserService userService = UserService.getInstance();
    SubjectService subjectService = SubjectService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {

        //Set<Subject> subjects = subjectService.getSubjects();
        //request.setAttribute("subjects", subjects);
        User user = (User)((HttpServletRequest)request).getSession().getAttribute(Constants.USER_SESSION_ATTRIBUTE);
        
        return userService.processUser(request, user);
//        return Constants.ADMIN_PAGE;
    }

}
