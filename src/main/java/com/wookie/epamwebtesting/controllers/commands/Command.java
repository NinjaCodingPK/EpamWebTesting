/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class implements command pattern. 
 */
public interface Command {

    /**
     * Method executes a chosen command.
     * @param request
     * @param response
     * @return name of page or null if redirection takes place.
     * @throws ServletException
     * @throws IOException
     * @throws RuntimeException if some problem in model appears.
     */
    String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, RuntimeException;
}
