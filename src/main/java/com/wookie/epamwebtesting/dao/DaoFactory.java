package com.wookie.epamwebtesting.dao;


public abstract class DaoFactory {
    public abstract AnswerDao createAnswerDao();
    public abstract SubjectDao createSubjectDao();
    public abstract TaskAnswersDao createTaskAnswersDao();
    public abstract TaskDao createTaskDao();
    public abstract TestDao createTestDao();
    public abstract TestTasksDao createTestTasksDao();
    public abstract UserDao createUserDao();
    public abstract RightsDao createRightsDao();

    public static DaoFactory getFactory() {
        //return new JdbcDaoFactory();
        try {
            return (DaoFactory) Class.forName(
                    "com.wookie.webtoster.dao.jdbc.JdbcDaoFactory").newInstance();
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
