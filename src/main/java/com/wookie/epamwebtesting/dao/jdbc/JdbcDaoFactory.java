package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class JdbcDaoFactory extends DaoFactory {

    private static DataSource ds;
//    public static final String HOST = "jdbc:mysql://localhost:3306/testing"
//            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
//            + "serverTimezone=UTC&useSSL=false";
//    public static final String ROOT = "root";
//    public static final String PASSWORD = "1234";

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            //ds = (DataSource) ic.lookup("jdbc/WebTosterResource");
            ds = (DataSource) ic.lookup("jdbc/WebTosterResource");
        } catch (NamingException e) {
            //e.printStackTrace();
        }
    }

    static Connection getConnection() throws SQLException {
        /*try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(HOST, ROOT, PASSWORD);*/
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

}
