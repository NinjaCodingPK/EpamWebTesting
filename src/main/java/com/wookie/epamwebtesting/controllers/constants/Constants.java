/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.constants;

/**
 * Class handles necessary constants.
 */
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
    public static final String ERROR_PAGE = "WEB-INF/pages/errorPage.jsp";
    public static final String STATISTIC_PAGE  = "WEB-INF/pages/userStatisticPage.jsp";

    public static final String REDIRECT_UPDATE_PAGE = "./Controller?command=TO_UPDATE_TEST&testId=";
    public static final String REDIRECT_ADMIN_PAGE = "./Controller?command=USER_PAGE";

    public static final String USER_SESSION_ATTRIBUTE = "currentUser";
    public static final String TEST_UPDATE_SESSION_ATTRIBUTE = "updateTest";

    public static final String ADMIN_RIGHTS = "Admin";
    public static final String TUTOR_RIGHTS = "Tutor";
    public static final String STUDENT_RIGHTS = "Student";
    
    public static final String PROPERTY_USER_NAME = "name";
    public static final String PROPERTY_USER_SURNAME = "surname";
    public static final String PROPERTY_USER_LOGIN = "login";
    public static final String PROPERTY_USER_PASSWORD = "password";
    public static final String PROPERTY_USER_RIGHTS = "rights";
    public static final String PROPERTY_SUBJECT_NAME = "subjectname";
    public static final String PROPERTY_SUBJECT_ID = "subjectId";
    public static final String PROPERTY_TASK_ID = "taskId";
    public static final String PROPERTY_TASK_QUESTION = "question";
    public static final String PROPERTY_TASK_QUESTION_ID = "question_id";
    public static final String PROPERTY_TASK_ANSWER = "answ";
    public static final String PROPERTY_TASK_TOUGHNESS = "toughness";
    public static final String ATTRIBUTE_SUBJECTS = "subjects";
    public static final String ATTRIBUTE_TEST = "test";
    public static final String ATTRIBUTE_TASK = "task";
    public static final String ATTRIBUTE_ANSWERS = "answers";
    public static final String ATTRIBUTE_TUTOR = "tutor";
    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_RESULT = "result";
    public static final String ATTRIBUTE_SUBJECT = "subject";
    public static final String PROPERTY_TEST_ID = "testId";
    public static final String PROPERTY_RESULT_LIST = "resultlist";
    public static final String PROPERTY_TEST_LIST = "testlist";
    public static final String PROPERTY_SUBJECT_LIST = "subjectlist";
    public static final String PROPERTY_TUTOR_LIST = "tutorlist";
    public static final String PROPERTY_COMMAND = "command";
    
    public static final String ERROR_LOGIN = "login error";
}

