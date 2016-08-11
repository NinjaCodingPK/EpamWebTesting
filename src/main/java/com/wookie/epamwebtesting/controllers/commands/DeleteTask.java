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


public class DeleteTask implements Command {
    private TaskService taskService = TaskService.getInstance();

    /**
     * Command class. Handles addtest.jsp page. 
     * Method calls task service for deleting task from database.
     * @param request
     * @param response
     * @return name of existing command for redirection.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, RuntimeException {

        int taskId = Integer.parseInt(request.getParameter(Constants.PROPERTY_TASK_ID));
        int testId = Integer.parseInt(request.getParameter(Constants.PROPERTY_TEST_ID));
        taskService.deleteTask(taskId, testId);
        return Constants.REDIRECT_UPDATE_PAGE + testId;  // Using existing command for redirection.
    }

}
