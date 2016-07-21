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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {

//        System.out.println(request.getPathInfo());
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        int testid = Integer.parseInt(request.getParameter("testId"));
        taskService.deleteTask(taskId);
        return Constants.REDIRECT_UPDATE_PAGE + testid;
    }

}
