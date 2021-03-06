package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.TaskDao;
import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
import com.wookie.epamwebtesting.entities.builder.TaskBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.*;


public class JdbcTaskDao extends JdbcConnectorDao implements TaskDao {
    private static final Logger logger = LogManager.getLogger(JdbcTaskDao.class);
    public static final String CREATE_STATEMENT =
            "INSERT INTO Task (text, toughness) VALUES (?, ?);";
    public static final String UPDATE_STATEMENT =
            "UPDATE Task SET text=?, toughness=? WHERE id=?;";
    public static final String DELETE_ANSWERS_ENTRIES_STATEMENT =
            "DELETE FROM TaskAnswers WHERE taskId = ?;";
    public static final String DELETE_TESTS_ENTRIES_STATEMENT =
            "DELETE FROM TestTasks WHERE taskId = ?;";
    public static final String DELETE_STATEMENT =
            "DELETE FROM Task WHERE id = ?;";
    public static final String SEARCH_ID =
            "SELECT * from Task WHERE id = ?;";
    public static final String FIND_ALL_STATEMENT =
            "SELECT * from Task;";
    public static final String FIND_BY_TEXT_STATEMENT =
            "SELECT * from Task WHERE text=?;";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_TOUGHNESS = "toughness";
    
    private Task getResult(ResultSet rs) throws SQLException {
        return new TaskBuilder()
                        .setId(rs.getInt(COLUMN_ID))
                        .setText(rs.getString(COLUMN_TEXT))
                        .setToughness(rs.getInt(COLUMN_TOUGHNESS))
                        .build();
    }
    
    @Override
    public Task create(Task task) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT,  Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getText());
            preparedStatement.setInt(2, task.getToughness());
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); 
            if (generatedKeys.next()) 
                task.setId(generatedKeys.getInt(1));
            
            preparedStatement.close();
            
            return task;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Task task) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setString(1, task.getText());
            preparedStatement.setInt(2, task.getToughness());
            preparedStatement.setInt(3, task.getId());
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
            PreparedStatement preparedStatement = cn.prepareStatement(DELETE_ANSWERS_ENTRIES_STATEMENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
//            preparedStatement = cn.prepareStatement(DELETE_TESTS_ENTRIES_STATEMENT);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
            preparedStatement = cn.prepareStatement(DELETE_STATEMENT);
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
    public Task findById(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Task temp = null;
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
    public Set<Task> findAll() {
        try (Connection cn = getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<Task> res = new HashSet<>();
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
    
    public Task getByText(String text) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_TEXT_STATEMENT);
            preparedStatement.setString(1, text);
            ResultSet rs = preparedStatement.executeQuery();

            Task temp = null;
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
}
