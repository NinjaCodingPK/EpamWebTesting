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


public class ToUpdateTask implements Command {

    TaskService taskService = TaskService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {

        int id = Integer.parseInt(request.getParameter("taskId"));
        Task task = taskService.getTask(id);
//        String[] answers = taskService.getAnswersForTask(task.getId());
       List<String> answers = taskService.getAnswersForTask(task.getId()); 

        request.setAttribute("task", task);
        request.setAttribute("answers", answers);

        return Constants.ADD_TASK_PAGE;
    }

}
