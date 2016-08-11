/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.StudentTestsDao;
import com.wookie.epamwebtesting.entities.StudentTests;
import com.wookie.epamwebtesting.entities.TaskAnswers;
import com.wookie.epamwebtesting.entities.builder.StudentTestsBuilder;
import com.wookie.epamwebtesting.entities.builder.TaskAnswersBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.*;

/**
 *
 * @author wookie
 */
public class JdbcStudentTestsDao extends JdbcConnectorDao implements StudentTestsDao {
    private static final Logger logger = LogManager.getLogger(JdbcStudentTestsDao.class);
    public static final String CREATE_STATEMENT =
            "INSERT INTO StudentTests (studentId, testId, result) VALUES (?, ?, ?);";
    public static final String UPDATE_STATEMENT =
            "UPDATE StudentTests SET result=? WHERE studentId=? AND testId=?;";
    public static final String DELETE_BY_STUDENT_STATEMENT =
            "DELETE FROM StudentTests WHERE studentId=?;";
    public static final String DELETE_BY_TEST_STATEMENT =
            "DELETE FROM StudentTests WHERE testId=?;";
    public static final String SEARCH_BY_ID_STATEMENT =
            "SELECT * from StudentTests WHERE studentId = ?;";
    public static final String FIND_ALL_STATEMENT =
            "SELECT * from StudentTests;";
    public static final String FIND_BY_USER_STATEMENT =
            "SELECT * from StudentTests WHERE studentId=?;";
    public static final String FIND_BY_TEST_STATEMENT =
            "SELECT * from StudentTests WHERE testId=?;";
    public static final String GET_RESULT_STATEMENT =
            "SELECT result FROM StudentTests WHERE studentId=? AND testId=?;";
    
    public static final String COLUMN_STUDENT_ID = "studentId";
    public static final String COLUMN_TEST_ID = "testId";
    public static final String COLUMN_RESULT = "result";

    private StudentTests getResult(ResultSet rs) throws SQLException {
        return new StudentTestsBuilder()
                .setStudentId(rs.getInt(COLUMN_STUDENT_ID))
                .setTestId(rs.getInt(COLUMN_TEST_ID))
                .setResult(rs.getInt(COLUMN_RESULT))
                .build();
    }
    
    @Override
    public StudentTests create(StudentTests studentTests) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT);
            preparedStatement.setInt(1, studentTests.getStudentId());
            preparedStatement.setInt(2, studentTests.getTestId());
            preparedStatement.setInt(3, studentTests.getResult());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            return studentTests;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(StudentTests studentTests) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setInt(1, studentTests.getResult());
            preparedStatement.setInt(2, studentTests.getStudentId());
            preparedStatement.setInt(3, studentTests.getTestId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            return false;
        }
    }

    @Override 
    public boolean delete(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(DELETE_BY_TEST_STATEMENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            return false;
        }
    }

    @Override
    public StudentTests findById(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_BY_ID_STATEMENT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            StudentTests temp = null;
            if(rs.next()) {
                temp = getResult(rs);
            }
            preparedStatement.close();
            return temp;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<StudentTests> findAll() {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<StudentTests> res = new HashSet<>();
            while (rs.next()) {
                res.add(getResult(rs));
            }
            query.close();
            return res;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<StudentTests> getByStudent(int studentId) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_USER_STATEMENT);
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();

            Set<StudentTests> temp = new HashSet<>();
            while(rs.next()) {
                temp.add(getResult(rs));
            }
            preparedStatement.close();
            return temp;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getResult(int studentId, int testId) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(GET_RESULT_STATEMENT);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, testId);
            ResultSet rs = preparedStatement.executeQuery();

            Integer result = null;
            if(rs.next()) {
                result = rs.getInt(COLUMN_RESULT);
            }
            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }
    
    
}

