package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.entities.StudentTests;


public abstract class DaoFactory {
    public final static String FACTORY_PASS = "com.wookie.epamwebtesting.dao.jdbc.JdbcDaoFactory";
    public abstract AnswerDao createAnswerDao();
    public abstract SubjectDao createSubjectDao();
    public abstract TaskAnswersDao createTaskAnswersDao();
    public abstract TaskDao createTaskDao();
    public abstract TestDao createTestDao();
    public abstract TestTasksDao createTestTasksDao();
    public abstract UserDao createUserDao();
    public abstract RightsDao createRightsDao();
    public abstract StudentTestsDao createStudentTestsDao();

    public static DaoFactory getFactory() {
        //return new JdbcDaoFactory();
        try {
            return (DaoFactory) Class.forName(FACTORY_PASS).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
