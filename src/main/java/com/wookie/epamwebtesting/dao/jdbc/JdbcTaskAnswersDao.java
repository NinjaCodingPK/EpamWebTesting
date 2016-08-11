package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.TaskAnswersDao;
import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.entities.TaskAnswers;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
import com.wookie.epamwebtesting.entities.builder.TaskAnswersBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.*;

public class JdbcTaskAnswersDao extends JdbcConnectorDao implements TaskAnswersDao {
    private static final Logger logger = LogManager.getLogger(JdbcTaskAnswersDao.class);
    public static final String CREATE_STATEMENT =
            "INSERT INTO TaskAnswers (taskId, answerId, correctness) VALUES (?, ?, ?);";
    public static final String UPDATE_STATEMENT =
            "UPDATE TaskAnswers SET correctness=? WHERE taskId=? AND answerId=?;";
    public static final String DELETE_STATEMENT =
            "DELETE FROM TaskAnswers WHERE nswerId=?;";
    public static final String SEARCH_BY_ID_STATEMENT =
            "SELECT * from TaskAnswers WHERE taskId = ?;";
    public static final String FIND_ALL_STATEMENT =
            "SELECT * from TaskAnswers;";
    public static final String FIND_BY_TASK_STATEMENT =
            "SELECT * from TaskAnswers WHERE taskId=?;";
    public static final String FIND_BY_ANSWER_STATEMENT =
            "SELECT * from TaskAnswers WHERE answerId=?;";
    public static final String GET_CORRECTNESS_STATEMENT =
            "SELECT correctness FROM TaskAnswers WHERE taskId=? AND answerId=?;";
    
    public static final String COLUMN_TASK_ID = "taskId";
    public static final String COLUMN_ANSWER_ID = "answerId";
    public static final String COLUMN_CORRECTNESS = "correctness";

    private TaskAnswers getResult(ResultSet rs) throws SQLException {
        return new TaskAnswersBuilder()
                        .setTaskId(rs.getInt(COLUMN_TASK_ID))
                        .setAnswerId(rs.getInt(COLUMN_ANSWER_ID))
                        .setCorrectness(rs.getBoolean(COLUMN_CORRECTNESS))
                        .build();
    }
    
    @Override
    public TaskAnswers create(TaskAnswers taskAnswers) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT);
            preparedStatement.setInt(1, taskAnswers.getTaskId());
            preparedStatement.setInt(2, taskAnswers.getAnswerId());
            preparedStatement.setBoolean(3, taskAnswers.isCorrectness());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            return taskAnswers;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(TaskAnswers taskAnswers) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setBoolean(1, taskAnswers.isCorrectness());
            preparedStatement.setInt(2, taskAnswers.getTaskId());
            preparedStatement.setInt(3, taskAnswers.getAnswerId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            return false;
        }
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
            logger.error("Error while processing database " + logger.getName());
            return false;
        }
    }

    @Override
    public TaskAnswers findById(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_BY_ID_STATEMENT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            TaskAnswers temp = null;
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
    public Set<TaskAnswers> findAll() {
        try (Connection cn = getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<TaskAnswers> res = new HashSet<>();
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
    public Set<TaskAnswers> findByAnswerId(int answerId) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_TASK_STATEMENT);
            preparedStatement.setInt(1, answerId);
            ResultSet rs = preparedStatement.executeQuery();

            Set<TaskAnswers> temp = new HashSet<>();
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
    public Set<TaskAnswers> findByTaskId(int taskId) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_TASK_STATEMENT);
            preparedStatement.setInt(1, taskId);
            ResultSet rs = preparedStatement.executeQuery();

            Set<TaskAnswers> temp = new HashSet<>();
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
    public boolean getCorrectness(int taskId, int answerId) {
         try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(GET_CORRECTNESS_STATEMENT);
            preparedStatement.setInt(1, taskId);
            preparedStatement.setInt(2, answerId);
            ResultSet rs = preparedStatement.executeQuery();
            
            boolean result;
            if(rs.next()) {
                result = rs.getBoolean(COLUMN_CORRECTNESS);
            } else {
                result = false;
            }
            preparedStatement.close();
            return result;
        } catch (SQLException e) {
           logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    
}
