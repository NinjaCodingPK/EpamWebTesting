/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.services;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.controllers.filters.AuthorizationFilter;
import com.wookie.epamwebtesting.dao.DaoFactory;
import com.wookie.epamwebtesting.dao.RightsDao;
import com.wookie.epamwebtesting.dao.UserDao;
import com.wookie.epamwebtesting.entities.StudentTests;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.entities.User;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);  
    private static UserService instance = new UserService();
    
    private TestService testService = TestService.getInstance();
    private SubjectService subjectService = SubjectService.getInstance();
    private UserDao userDao = DaoFactory.getFactory().createUserDao();
    private RightsDao rightsDao = DaoFactory.getFactory().createRightsDao();

    public static UserService getInstance() {
        return instance;
    }
    
    /**
     * Method finds user by his login.
     * @param login user's login.
     * @return User instance.
     * @throws RuntimeException 
     */
    public User getUser(String login) throws RuntimeException {
        User user = userDao.getByLogin(login);
        user.getRights().setName(rightsDao.findById(user.getRights().getId()).getName());

        return user;
    }

    /**
     * Method checks user's password
     * @param user User instance.
     * @param password inputed password.
     * @throws RuntimeException if password is wrong.
     */
    public void checkPassword(User user, String password) throws RuntimeException {
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException();
        }
    }

    /**
     * Method checks user's rights and decides in which page current user should
     * be redirected.
     *
     * @param request Http request.
     * @param user instance of User class.
     * @return name of page in which user should be redirected.
     * @throws RuntimeException if DAO classes throws exception or error while logging in appears.
     */
    public String processUser(HttpServletRequest request, User user) throws RuntimeException {
        logger.info("Starting process user");

        Set subjects = subjectService.getSubjects();

        switch (user.getRights().getName()) {
            case Constants.TUTOR_RIGHTS:
                Map tests = testService.fillTests(testService.getByUser(user.getId()));
                request.setAttribute(Constants.PROPERTY_RESULT_LIST, tests);
                return Constants.TUTOR_PAGE;
            case Constants.STUDENT_RIGHTS:
                Set tutors = userDao.findByRights(Constants.TUTOR_RIGHTS);
                request.setAttribute(Constants.PROPERTY_TUTOR_LIST, tutors);
                request.setAttribute(Constants.PROPERTY_SUBJECT_LIST, subjects);
                return Constants.STUDENT_PAGE;
            case Constants.ADMIN_RIGHTS:
                request.setAttribute(Constants.PROPERTY_SUBJECT_LIST, subjects);
                return Constants.ADMIN_PAGE;
            default:
                logger.error("Error while loggin in");
                throw new RuntimeException();
        }

    }
    
}
