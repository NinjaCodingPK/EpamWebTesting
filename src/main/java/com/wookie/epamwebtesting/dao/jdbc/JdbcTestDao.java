package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.TaskDao;
import com.wookie.epamwebtesting.dao.TestDao;
import com.wookie.epamwebtesting.dao.TestTasksDao;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
import com.wookie.epamwebtesting.entities.builder.TestBuilder;
import com.wookie.epamwebtesting.entities.builder.UserBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.*;


public class JdbcTestDao extends JdbcConnectorDao implements TestDao {
    private static final Logger logger = LogManager.getLogger(JdbcTestDao.class);
    public static final String CREATE_STATEMENT =
            "INSERT INTO Test (tutor, subject) VALUES (?, ?);";
    public static final String UPDATE_STATEMENT =
            "UPDATE Test SET tutor=?, subject=? WHERE id=?;";
    public static final String DELETE_ENTRIES_STATEMENT =
            "DELETE FROM TestTasks WHERE testId = ?;";
    public static final String DELETE_STATEMENT =
            "DELETE FROM Test WHERE id = ?;";
    public static final String FIND_BY_ID_STATEMENT =
            "SELECT * from Test WHERE id = ?;";
    public static final String FIND_ALL_STATEMENT =
            "SELECT * from Test;";
    public static final String FIND_BY_SUBJECT_STATEMENT =
            "Select Test.id, Test.tutor, Test.subject from\n" +
                    "Test, Subject\n" +
                    "where \n" +
                    "(Test.subject = ?) and\n" +
                    "(Subject.id = Test.subject) \n" +
                    "group by Test.id;";
    public static final String FIND_BY_USER_STATEMENT =
            "Select Test.id, Test.tutor, Test.subject from\n" +
                    "Test, User\n" +
                    "where \n" +
                    "(User.id = Test.tutor) and\n" +
                    "(Test.tutor = ?) \n" +
                    "group by Test.id;";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_TUTOR = "tutor";
    
    private Test getResult(ResultSet rs) throws SQLException {
        return new TestBuilder()
                        .setId(rs.getInt(COLUMN_ID))
                        .setSubject(new SubjectBuilder().setId(rs.getInt(COLUMN_SUBJECT)).build())
                        .setTutor(new UserBuilder().setId(rs.getInt(COLUMN_TUTOR)).build())
                        .build();
    }
    
    @Override
    public Test create(Test test) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, test.getTutor().getId());
            preparedStatement.setInt(2, test.getSubject().getId());
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); 
            if (generatedKeys.next()) 
                test.setId(generatedKeys.getInt(1));
           
            preparedStatement.close();
            
            return test;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    
    @Override
    public boolean update(Test test) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setInt(1, test.getTutor().getId());
            preparedStatement.setInt(2, test.getSubject().getId());
            preparedStatement.setInt(3, test.getId());
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
            PreparedStatement preparedStatement = cn.prepareStatement(DELETE_ENTRIES_STATEMENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
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
    public Test findById(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_ID_STATEMENT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Test temp = null;
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

    // Don't need this.
    @Override
    public Set<Test> findAll() {
        try (Connection cn = getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<Test> res = new HashSet<>();
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
    public Set<Test> findBySubject(int subjectId) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_SUBJECT_STATEMENT);
            preparedStatement.setInt(1, subjectId);
            ResultSet rs = preparedStatement.executeQuery();
            Set<Test> res = new HashSet<>();
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
    
    public Set<Test> findByUser(int userId) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_USER_STATEMENT);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            Set<Test> res = new HashSet<>();
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
