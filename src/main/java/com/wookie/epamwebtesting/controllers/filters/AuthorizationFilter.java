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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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
   Map<String, String> commandRights = new HashMap<>();

   {
       commandRights.put("DELETE_SUBJECT", Constants.ADMIN_RIGHTS);
       commandRights.put("ADD_SUBJECT", Constants.ADMIN_RIGHTS);
       commandRights.put("ADMIN_PAGE", Constants.ADMIN_RIGHTS);
       commandRights.put("SHOW_TUTORS_TESTS", Constants.TUTOR_RIGHTS);
       commandRights.put("TO_ADD_TASK", Constants.TUTOR_RIGHTS);
       commandRights.put("TO_UPDATE_TASK", Constants.TUTOR_RIGHTS);
       commandRights.put("TO_UPDATE_TEST", Constants.TUTOR_RIGHTS);
       commandRights.put("ADD_TASK", Constants.TUTOR_RIGHTS);
       commandRights.put("ADD_TEST", Constants.TUTOR_RIGHTS);
       commandRights.put("DELETE_TASK", Constants.TUTOR_RIGHTS);
       commandRights.put("SHOW_TESTS_BY_TUTOR", Constants.STUDENT_RIGHTS);
       commandRights.put("SHOW_TESTS_BY_SUBJECT", Constants.STUDENT_RIGHTS);
       commandRights.put("SHOW_STATISTIC", Constants.STUDENT_RIGHTS);
   }
    /**
     * Default constructor. 
     */
    public AuthorizationFilter() {
        
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    	// TODO Auto-generated method stub
    }

    /**
     * Method check rights of user to access a web page.
     * @param request
     * @param response
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException, RuntimeException {

        User user = (User)((HttpServletRequest)request).getSession().getAttribute(Constants.USER_SESSION_ATTRIBUTE);
        
        String currentCommand = request.getParameter(Constants.PROPERTY_COMMAND);
        
        if(currentCommand != null) {
            try {
                String neededRight = commandRights.get(currentCommand);
                if(neededRight != null)
                    if(!commandRights.get(currentCommand).equals(user.getRights().getName())) {
                        throw new RuntimeException(Constants.ERROR_LOGIN);
                    }
                 
            } catch (NullPointerException e) {
                throw new RuntimeException(Constants.ERROR_LOGIN);
            }
        }
        
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        
    }

}
