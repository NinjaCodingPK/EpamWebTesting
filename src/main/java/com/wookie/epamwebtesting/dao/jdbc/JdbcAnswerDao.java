package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.AnswerDao;
import com.wookie.epamwebtesting.entities.Answer;
import com.wookie.epamwebtesting.entities.builder.AnswerBuilder;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.*;


public class JdbcAnswerDao extends JdbcConnectorDao implements AnswerDao {
    private static final Logger logger = LogManager.getLogger(JdbcAnswerDao.class);
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

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEXT = "text";
    
    private Answer getResult(ResultSet rs) throws SQLException {
        return new AnswerBuilder()
                    .setId(rs.getInt(COLUMN_ID))
                    .setText(rs.getString(COLUMN_TEXT))
                    .build();
    }
    
    @Override
    public Answer create(Answer answer) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT,  Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, answer.getText());
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); 
            if (generatedKeys.next()) 
                answer.setId(generatedKeys.getInt(1));
            
            preparedStatement.close();
            
            return answer;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Answer answer) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setString(1, answer.getText());
            preparedStatement.setInt(2, answer.getId());
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
    public Answer findById(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Answer temp = null;
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
    public Set<Answer> findAll() {
        try (Connection cn = getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<Answer> res = new HashSet<>();
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
    public Answer getByText(String text) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_TEXT_STATEMENT);
            preparedStatement.setString(1, text);
            ResultSet rs = preparedStatement.executeQuery();

            Answer temp = null;
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
