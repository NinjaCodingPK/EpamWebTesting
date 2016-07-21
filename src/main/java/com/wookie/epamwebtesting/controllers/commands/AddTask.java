/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.services.TaskService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddTask implements Command {

    TaskService taskService = TaskService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        Integer taskId = Integer.parseInt(request.getParameter("id"));
        String question = request.getParameter("question");
        String[] answers = request.getParameterValues("answ");
        Integer toughness = Integer.parseInt(request.getParameter("toughness"));
        HttpSession session = request.getSession();
        Integer testId = (Integer) session.getAttribute(Constants.TEST_UPDATE_SESSION_ATTRIBUTE);

        taskService.addTask(taskId, testId, question, answers, toughness);

        response.sendRedirect(Constants.REDIRECT_UPDATE_PAGE + testId.toString());
        return null;
    }

}
