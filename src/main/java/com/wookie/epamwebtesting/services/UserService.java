/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.services;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.dao.DaoFactory;
import com.wookie.epamwebtesting.dao.RightsDao;
import com.wookie.epamwebtesting.dao.UserDao;
import com.wookie.epamwebtesting.entities.User;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

public class UserService {

    private static UserService instance = new UserService();

    public static UserService getInstance() {
        return instance;
    }

//    public void createUser(String name, String surname, String login, String password, String rights) {
//        UserDao userDao = DaoFactory.getFactory().createUserDao();
//        userDao.create(new UserBuilder()
//                .setName(name)
//                .setSurname(surname)
//                .setLogin(login)
//                .setPassword(password)
//                .setRights(2) //TODO
//                .build());
//    }
    
    public User getUser(String login) throws RuntimeException {
        UserDao userDao = DaoFactory.getFactory().createUserDao();
        RightsDao rightsDao = DaoFactory.getFactory().createRightsDao();
        User user = userDao.getByLogin(login);
        user.getRights().setName(rightsDao.findById(user.getRights().getId()).getName());

        return user;
    }

    public void checkPassword(User user, String password) throws Exception {
        if (!user.getPassword().equals(password)) {
            throw new Exception();
        }
    }

    /**
     * Method checks user's rights and decides in which page current user should
     * be redirected.
     *
     * @param request Http request.
     * @param user instance of User class.
     * @return name of page in which user should be redirected.
     * @throws RuntimeException if DAO classes throws exception.
     * @throws Exception if login is wrong.
     */
    public String processUser(HttpServletRequest request, User user) throws RuntimeException {
        TestService testService = TestService.getInstance();
        SubjectService subjectService = SubjectService.getInstance();
        UserDao userDao = DaoFactory.getFactory().createUserDao();

        Set subjects = subjectService.getSubjects();

        switch (user.getRights().getName()) {
            case "Tutor":
                Map tests = testService.fillTests(testService.getByUser(user.getId()));
                request.setAttribute("resultlist", tests);
                return Constants.TUTOR_PAGE;
            case "Student":
                Set tutors = userDao.findByRights("Tutor");
                request.setAttribute("tutorslist", tutors);
                request.setAttribute("subjectlist", subjects);
                return Constants.STUDENT_PAGE;
            case "Admin":
                request.setAttribute("subjects", subjects);
                return Constants.ADMIN_PAGE;
            default:
                throw new RuntimeException();
        }

    }
    
//    public Set<String> showRights() {
//        UserDao userDao = DaoFactory.getFactory().createUserDao();
//
//        Set<Dao> rights = userDao.getRights();
//        rights.remove("admin");
//        return rights;
//    }
}
