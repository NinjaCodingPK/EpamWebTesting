package com.wookie.epamwebtesting.dao.jdbc;


import com.wookie.epamwebtesting.dao.DaoFactory;
import com.wookie.epamwebtesting.dao.TaskDao;
import com.wookie.epamwebtesting.dao.TestTasksDao;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.TaskAnswers;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.builder.TaskAnswersBuilder;
import com.wookie.epamwebtesting.entities.builder.TestTasksBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class JdbcTestTasksDao implements TestTasksDao {
    public static final String CREATE_STATEMENT =
            "INSERT INTO TestTasks (testId, taskId) VALUES (?, ?);";
    public static final String UPDATE_STATEMENT =
            "UPDATE TestTasks SET correctness=? WHERE taskId=? AND answerId=?;";
    public static final String DELETE_STATEMENT =
            "DELETE FROM TestTasks WHERE nswerId=?;";
    public static final String SEARCH_ID =
            "SELECT * from TestTasks WHERE id=?;";
    public static final String FIND_ALL_STATEMENT =
            "SELECT * from TestTasks;";
    public static final String FIND_BY_TEST_STATEMENT = 
            "SELECT * FROM TestTasks WHERE testId=?;";
    public static final String FIND_BY_TASK_STATEMENT = 
            "SELECT * FROM TestTasks WHERE taskId=?;";

    @Override
    public TestTasks create(TestTasks taskAnswers) {
        //INSERT INTO `testing`.`Tutor` (`name`, `surname`, `login`, `password`) VALUES ('asd', 'asd', 'asd', 'asd');
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            //Statement query = cn.createStatement();
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT);
            preparedStatement.setInt(1, taskAnswers.getTestId());
            preparedStatement.setInt(2, taskAnswers.getTaskId());
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            
            return taskAnswers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(TestTasks taskAnswers) {
        throw new NotImplementedException();
//        try (Connection cn = JdbcDaoFactory.getConnection()) {
//            PreparedStatement preparedStatement = cn.prepareStatement(UPDATE_STATEMENT);
//            preparedStatement.setBoolean(1, taskAnswers.isCorrectness());
//            preparedStatement.setInt(2, taskAnswers.getTaskId());
//            preparedStatement.setInt(3, taskAnswers.getAnswerId());
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
    }

    @Override  //Not Okay.
    public boolean delete(int id) {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(DELETE_STATEMENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TestTasks findById(int id) {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            TestTasks temp = null;
            if(rs.next()) {
                temp = new TestTasksBuilder()
                        .setTaskId(rs.getInt("taskId"))
                        .setTestId(rs.getInt("testId"))
                        .build();
            }
            preparedStatement.close();
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<TestTasks> findAll() {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<TestTasks> res = new HashSet<>();
            while (rs.next()) {
                res.add(new TestTasksBuilder()
                        .setTaskId(rs.getInt("taskId"))
                        .setTestId(rs.getInt("testId"))
                        .build());
            }
            query.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Set<TestTasks> findByTestId(int testId) {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_TEST_STATEMENT);
     
            preparedStatement.setInt(1, testId);
            ResultSet rs = preparedStatement.executeQuery();
            
            Set<TestTasks> res = new HashSet<>();
            while (rs.next()) {
                res.add(new TestTasksBuilder()
                        .setTaskId(rs.getInt("taskId"))
                        .setTestId(rs.getInt("testId"))
                        .build());
            }
            preparedStatement.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
    }

    @Override
    public Set<TestTasks> findByTaskId(int taskId) {
         try (Connection cn = JdbcDaoFactory.getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_TASK_STATEMENT);
     
            preparedStatement.setInt(1, taskId);
            ResultSet rs = preparedStatement.executeQuery();
            
            Set<TestTasks> res = new HashSet<>();
            while (rs.next()) {
                res.add(new TestTasksBuilder()
                        .setTaskId(rs.getInt("taskId"))
                        .setTestId(rs.getInt("testId"))
                        .build());
            }
            preparedStatement.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
    }
}
