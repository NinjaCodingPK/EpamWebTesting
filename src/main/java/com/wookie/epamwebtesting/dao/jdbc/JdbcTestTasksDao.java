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
import org.apache.logging.log4j.*;


public class JdbcTestTasksDao extends JdbcConnectorDao implements TestTasksDao {
    private static final Logger logger = LogManager.getLogger(JdbcTestTasksDao.class);
    public static final String CREATE_STATEMENT =
            "INSERT INTO TestTasks (testId, taskId) VALUES (?, ?);";
    public static final String UPDATE_STATEMENT =
            "UPDATE TestTasks SET correctness=? WHERE taskId=? AND answerId=?;";
    public static final String DELETE_ENTRIES_STATEMENT =
            "DELETE FROM TestTasks WHERE testId=? AND taskId=?;";
    public static final String DELETE_STATEMENT =
            "DELETE FROM TestTasks WHERE testId=?;";
    public static final String SEARCH_ID =
            "SELECT * from TestTasks WHERE testId=?;";
    public static final String FIND_ALL_STATEMENT =
            "SELECT * from TestTasks;";
    public static final String FIND_BY_TEST_STATEMENT = 
            "SELECT * FROM TestTasks WHERE testId=?;";
    public static final String FIND_BY_TASK_STATEMENT = 
            "SELECT * FROM TestTasks WHERE taskId=?;";

    public static final String COLUMN_TASK_ID = "taskId";
    public static final String COLUMN_TEST_ID = "testId";
    
    private TestTasks getResult(ResultSet rs) throws SQLException {
        return new TestTasksBuilder()
                        .setTaskId(rs.getInt(COLUMN_TASK_ID))
                        .setTestId(rs.getInt(COLUMN_TEST_ID))
                        .build();
    }
    
    @Override
    public TestTasks create(TestTasks taskAnswers) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT);
            preparedStatement.setInt(1, taskAnswers.getTestId());
            preparedStatement.setInt(2, taskAnswers.getTaskId());
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            
            return taskAnswers;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(TestTasks taskAnswers) {
        throw new NotImplementedException();
    }

    @Override 
    public boolean delete(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(DELETE_STATEMENT);
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
    public boolean deleteEntries(int testId, int taskId) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(DELETE_ENTRIES_STATEMENT);
            preparedStatement.setInt(1, testId);
            preparedStatement.setInt(2, taskId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            return false;
        }
    }
    
    @Override
    public TestTasks findById(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            TestTasks temp = null;
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
    public Set<TestTasks> findAll() {
        try (Connection cn = getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<TestTasks> res = new HashSet<>();
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
    public Set<TestTasks> findByTestId(int testId) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_TEST_STATEMENT);
     
            preparedStatement.setInt(1, testId);
            ResultSet rs = preparedStatement.executeQuery();
            
            Set<TestTasks> res = new HashSet<>();
            while (rs.next()) {
                res.add(getResult(rs));
            }
            preparedStatement.close();
            return res;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        } 
    }

    @Override
    public Set<TestTasks> findByTaskId(int taskId) {
         try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_TASK_STATEMENT);
     
            preparedStatement.setInt(1, taskId);
            ResultSet rs = preparedStatement.executeQuery();
            
            Set<TestTasks> res = new HashSet<>();
            while (rs.next()) {
                res.add(getResult(rs));
            }
            preparedStatement.close();
            return res;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
        
    }
}
