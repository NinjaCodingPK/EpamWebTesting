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

    /**
     * Command class. Handles tutorpage.jsp page. 
     * Method forms a empty data for sending it into addtask jsp page.
     * It's necessary because "update task" and "create task" methods
     * are handled by the same jsp page.
     * @param request
     * @param response
     * @return name of page for forwarding.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some mistake in model arises. 
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
        request.setAttribute(Constants.ATTRIBUTE_TASK, new Task());  
        request.setAttribute(Constants.ATTRIBUTE_ANSWERS, new String[4]);

        int id = Integer.parseInt(request.getParameter(Constants.PROPERTY_TASK_ID));
        HttpSession session = request.getSession();
        session.setAttribute(Constants.TEST_UPDATE_SESSION_ATTRIBUTE, id);

        return Constants.ADD_TASK_PAGE;
    }

}
