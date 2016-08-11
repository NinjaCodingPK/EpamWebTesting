package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.*;
import com.wookie.epamwebtesting.entities.StudentTests;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class JdbcDaoFactory extends DaoFactory {
    private final static Logger logger = Logger.getLogger(JdbcDaoFactory.class.getName());
    private final static String POOL_NAME = "jdbc/WebTosterResource";
    private static DataSource ds;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup(POOL_NAME);
        } catch (NamingException e) {
            logger.warning("Error while processing database " + logger.getName());
        }
    }

    static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Override
    public AnswerDao createAnswerDao() {
        return new JdbcAnswerDao();
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new JdbcSubjectDao();
    }

    @Override
    public TaskAnswersDao createTaskAnswersDao() {
        return new JdbcTaskAnswersDao();
    }

    @Override
    public TaskDao createTaskDao() {
        return new JdbcTaskDao();
    }

    @Override
    public TestDao createTestDao() {
        return new JdbcTestDao();
    }

    @Override
    public TestTasksDao createTestTasksDao() {
        return new JdbcTestTasksDao();
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao();
    }

    @Override
    public RightsDao createRightsDao() {
        return new JdbcRightsDao();
    }

    public StudentTestsDao createStudentTestsDao() {
        return new JdbcStudentTestsDao();
    }
}
