/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.constants;


public class Constants {

    public static final String LOGIN_PAGE = "index.jsp";
    public static final String SUBJECT_PAGE = "WEB-INF/pages/subjectpage.jsp";
    public static final String SHOW_TEST_PAGE = "WEB-INF/pages/testpage.jsp";
    public static final String SHOW_ALL_TESTS_PAGE = "WEB-INF/pages/alltests.jsp";
    public static final String SHOW_SUBJECTS_PAGE = "WEB-INF/pages/subjectpage.jsp";
    public static final String RESULT_PAGE = "WEB-INF/pages/testresult.jsp";
    public static final String TUTOR_PAGE = "WEB-INF/pages/tutorpage.jsp";
    public static final String STUDENT_PAGE = "WEB-INF/pages/studentpage.jsp";
    public static final String ADD_TASK_PAGE = "WEB-INF/pages/addtask.jsp";
    public static final String ADD_TEST_PAGE = "WEB-INF/pages/addtest.jsp";
    public static final String ADMIN_PAGE = "WEB-INF/pages/adminpage.jsp";
    public static final String PROFILE_PAGE = "WEB-INF/pages/profilePage.jsp";
    public static final String ERROR_PAGE  = "WEB-INF/pages/errorPage.jsp";

    public static final String REDIRECT_UPDATE_PAGE = "./Controller?command=TO_UPDATE_TEST&testId=";
    public static final String REDIRECT_ADMIN_PAGE = "./Controller?command=USER_PAGE";

    public static final String USER_SESSION_ATTRIBUTE = "currentUser";
    public static final String TEST_UPDATE_SESSION_ATTRIBUTE = "updateTest";

    public static final String ADMIN_RIGHTS = "Admin";
    public static final String TUTOR_RIGHTS = "Tutor";
    public static final String STUDENT_RIGHTS = "Student";
    
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_RIGHTS = "rights";

}
