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

    /**
     * Command class. Handles addtask.jsp page. 
     * Method calls task service for adding a task into database.
     * @param request
     * @param response
     * @return null because of using a redirection.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        Integer taskId = Integer.parseInt(request.getParameter(Constants.PROPERTY_TASK_ID));
        String question = request.getParameter(Constants.PROPERTY_TASK_QUESTION);
        String[] answers = request.getParameterValues(Constants.PROPERTY_TASK_ANSWER);
        Integer toughness = Integer.parseInt(request.getParameter(Constants.PROPERTY_TASK_TOUGHNESS));
        HttpSession session = request.getSession();
        Integer testId = (Integer) session.getAttribute(Constants.TEST_UPDATE_SESSION_ATTRIBUTE);
        
        taskService.addTask(taskId, testId, question, answers, toughness);

        response.sendRedirect(Constants.REDIRECT_UPDATE_PAGE + testId.toString());
        return null;
    }

}
