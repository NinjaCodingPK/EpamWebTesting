/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.filters;

import com.wookie.epamwebtesting.controllers.commands.CommandList;
import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.entities.User;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthorizationFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    	// TODO Auto-generated method stub
    }

    /**
     * @param request
     * @param response
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {

        User user = (User)((HttpServletRequest)request).getSession().getAttribute(Constants.USER_SESSION_ATTRIBUTE);
        if(user == null)
        System.out.println("User is nulL!");
        
        String currentCommand = request.getParameter("command");
        if(currentCommand != null) {
            
            switch(currentCommand) {
                
                case "DELETE_SUBJECT" : 
                case "ADD_SUBJECT" :
                //case "REDIRECT_ADMIN_PAGE" :
                case "ADMIN_PAGE" :
                    try {
                        if(!Constants.ADMIN_RIGHTS.equals(user.getRights().getName())) {
                            throw new RuntimeException("login error");
                        }
                        break; 
                    } catch (NullPointerException e) {
                        throw new RuntimeException("login error");
                    }
                    
                case "SHOW_TUTORS_TESTS" :   
                case "TO_ADD_TASK" :    
                case "TO_UPDATE_TASK" :
                case "TO_UPDATE_TEST" :
                case "UPDATE_TEST" :
                case "ADD_TASK" :
                case "ADD_TEST" :
                case "DELETE_TASK" :
                    try {
                        if(!Constants.TUTOR_RIGHTS.equals(user.getRights().getName())) {
                            throw new RuntimeException("login error");
                        }
                        break;
                    } catch (NullPointerException e) {
                        throw new RuntimeException("login error");
                    }
                    
                case "SHOW_TESTS_BY_TUTOR" :    
                case "SHOW_TESTS_BY_SUBJECT" :  
                    try {
                        if(!Constants.STUDENT_RIGHTS.equals(user.getRights().getName())) {
                            throw new RuntimeException("login error");
                        }
                        break;
                    } catch (NullPointerException e) {
                        throw new RuntimeException("login error");
                    }    
            }
        }
        
        
//    	if(!"ok".equals(((HttpServletRequest)request).getSession().getAttribute("user"))){
//            throw new RuntimeException("login error");
//    	}
        
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    	// TODO Auto-generated method stub
    }

}
