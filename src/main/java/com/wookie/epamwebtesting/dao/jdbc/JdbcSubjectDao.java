package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.SubjectDao;
import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.*;


public class JdbcSubjectDao extends JdbcConnectorDao implements SubjectDao {
    private static final Logger logger = LogManager.getLogger(JdbcSubjectDao.class);
    public static final String CREATE_STATEMENT =
            "INSERT INTO Subject (name) VALUES (?);";
    public static final String UPDATE_STATEMENT =
            "UPDATE Subject SET name=? WHERE id=?;";
    public static final String DELETE_STATEMENT =
            "DELETE FROM Subject WHERE id = ?;";
    public static final String FIND_BY_ID_STATEMENT =
            "SELECT * from Subject WHERE id = ?;";
    public static final String FIND_ALL_STATEMENT =
            "SELECT * from Subject;";
     public static final String FIND_BY_SUBJECT_STATEMENT =
             "SELECT * from Subject WHERE name=?;";

     public static final String COLUMN_ID = "id";
     public static final String COLUMN_NAME = "name";
     
     private Subject getResult(ResultSet rs) throws SQLException {
         return new SubjectBuilder()
                    .setId(rs.getInt(COLUMN_ID))
                    .setName(rs.getString(COLUMN_NAME))
                    .build();
     }
     
    @Override
    public Subject create(Subject subject) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT,  Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); 
            if (generatedKeys.next()) 
                subject.setId(generatedKeys.getInt(1));
            
            preparedStatement.close();
            
            return subject;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Subject subject) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getId());
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
    public Subject findById(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_ID_STATEMENT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Subject temp = null;
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
    public Set<Subject> findAll() {
        try (Connection cn = getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<Subject> res = new HashSet<>();
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
    public Integer getId(String subject) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_SUBJECT_STATEMENT);
            preparedStatement.setString(1, subject);
            ResultSet rs = preparedStatement.executeQuery();
            
            Integer res = null;
            if(rs.next()) {
                 res = rs.getInt(COLUMN_ID);
                
            }
            preparedStatement.close();
            return res;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }
}
