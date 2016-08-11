/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.services.TaskService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ToUpdateTask implements Command {
    TaskService taskService = TaskService.getInstance();

    /**
     * Command class. Handles tutorpage.jsp page. 
     * Method forms a proper data for sending it into addtask jsp page.
     * @param request
     * @param response
     * @return name of page for forwarding.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, RuntimeException {

        int taskId = Integer.parseInt(request.getParameter(Constants.PROPERTY_TASK_ID));
        Task task = taskService.getTask(taskId);
        List<String> answers = taskService.getAnswersForTask(task.getId()); 
        int testId = Integer.parseInt(request.getParameter(Constants.PROPERTY_TEST_ID));
        HttpSession session = request.getSession();
        session.setAttribute(Constants.TEST_UPDATE_SESSION_ATTRIBUTE, testId);
        
        request.setAttribute(Constants.ATTRIBUTE_TASK, task);
        request.setAttribute(Constants.ATTRIBUTE_ANSWERS, answers);

        return Constants.ADD_TASK_PAGE;
    }

}
