/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.Task;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ToAddTask implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        request.setAttribute("task", new Task());
        request.setAttribute("answers", new String[4]);

        int id = Integer.parseInt(request.getParameter("taskId"));
        HttpSession session = request.getSession();
        session.setAttribute(Constants.TEST_UPDATE_SESSION_ATTRIBUTE, id);

        return Constants.ADD_TASK_PAGE;
    }

}
