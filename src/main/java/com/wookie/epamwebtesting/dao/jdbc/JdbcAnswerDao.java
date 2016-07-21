package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.AnswerDao;
import com.wookie.epamwebtesting.entities.Answer;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.User;
import com.wookie.epamwebtesting.entities.builder.AnswerBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class JdbcAnswerDao implements AnswerDao {
    public static final String CREATE_STATEMENT =
            "INSERT INTO Answer (text) VALUES (?);";
    public static final String UPDATE_STATEMENT =
            "UPDATE Answer SET text=? WHERE id=?;";
    public static final String DELETE_STATEMENT =
            "DELETE FROM Answer WHERE id = ?;";
    public static final String SEARCH_ID =
            "SELECT * from Answer WHERE id = ?;";
    public static final String FIND_ALL_STATEMENT =
            "SELECT * from Answer;";
    public static final String FIND_BY_TEXT_STATEMENT =
            "SELECT * from Answer WHERE text=?;";

    @Override
    public Answer create(Answer answer) {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT,  Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, answer.getText());
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); 
            if (generatedKeys.next()) 
                answer.setId(generatedKeys.getInt(1));
            
            preparedStatement.close();
            
            return answer;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Answer answer) {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setString(1, answer.getText());
            preparedStatement.setInt(2, answer.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
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
    public Answer findById(int id) {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Answer temp = null;
            if(rs.next()) {
                temp = new AnswerBuilder()
                    .setId(rs.getInt("id"))
                    .setText(rs.getString("text"))
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
    public Set<Answer> findAll() {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<Answer> res = new HashSet<>();
            while (rs.next()) {
                res.add(new AnswerBuilder()
                        .setId(rs.getInt("id"))
                        .setText(rs.getString("text"))
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
    public Answer getByText(String text) {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_TEXT_STATEMENT);
            preparedStatement.setString(1, text);
            ResultSet rs = preparedStatement.executeQuery();

            Answer temp = null;
            if(rs.next()) {
                temp = new AnswerBuilder()
                    .setId(rs.getInt("id"))
                    .setText(rs.getString("text"))
                    .build();
            }
            preparedStatement.close();
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
