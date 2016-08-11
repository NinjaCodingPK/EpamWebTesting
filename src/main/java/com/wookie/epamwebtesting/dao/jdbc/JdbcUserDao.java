package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.User;
import com.wookie.epamwebtesting.entities.builder.UserBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.wookie.epamwebtesting.dao.UserDao;
import com.wookie.epamwebtesting.entities.builder.RightsBuilder;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.*;


public class JdbcUserDao extends JdbcConnectorDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(JdbcUserDao.class);
    public static final String CREATE_STATEMENT =
            "INSERT INTO User (name, surname, login, password, rights) VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_STATEMENT =
            "UPDATE User SET name=?, surname=?, login=?, password=?, rights=? WHERE id=?;";
    public static final String DELETE_STATEMENT =
            "DELETE FROM User WHERE id = ?;";
    public static final String SEARCH_BY_ID_STATEMENT =
            "SELECT * from User WHERE id = ?;";
    public static final String SEARCH_BY_LOGIN_STATEMENT =
            "SELECT * from User WHERE login=?;";
    public static final String FIND_ALL_STATEMENT =
            "SELECT * from User;";
    public static final String FIND_BY_RIGHTS_STATEMENT =
            "SELECT User.id, User.name, User.surname, User.login, User.password, User.rights"
            + " from User, Rights WHERE "
            + "User.rights = Rights.id AND "
            + "Rights.name = ?;";
    
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_RIGHTS = "rights";
    
//    protected Connection getConnection() throws SQLException {
//        return JdbcDaoFactory.getConnection();
//    }
    
    private User getResult(ResultSet rs) throws SQLException {
        return new UserBuilder()
                        .setId(rs.getInt(COLUMN_ID))
                        .setName(rs.getString(COLUMN_NAME))
                        .setSurname(rs.getString(COLUMN_SURNAME))
                        .setLogin(rs.getString(COLUMN_LOGIN))
                        .setPassword(rs.getString(COLUMN_PASSWORD))
                        .setRights(new RightsBuilder().setId(rs.getInt(COLUMN_RIGHTS)).build())
                        .build();
    }
    
    @Override
    public User create(User user) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(CREATE_STATEMENT,  Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRights().getId());
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); 
            if (generatedKeys.next()) 
                user.setId(generatedKeys.getInt(1));
            
            preparedStatement.close();
            
            return user;
        } catch (SQLException e) {
            logger.error("Error while processing database " + logger.getName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User user) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRights().getId());
            preparedStatement.setInt(6, user.getId());
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
    public User findById(int id) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_BY_ID_STATEMENT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            User temp = null;
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
    public Set<User> findAll() {
        try (Connection cn = getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(FIND_ALL_STATEMENT);
            Set<User> res = new HashSet<>();
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
    
    public User getByLogin(String login) {
        try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_BY_LOGIN_STATEMENT);
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();

            User temp = null;
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
    
    public Set<User> findByRights(String rights) {
         try (Connection cn = getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(FIND_BY_RIGHTS_STATEMENT);
            preparedStatement.setString(1, rights);
            ResultSet rs = preparedStatement.executeQuery();

            Set<User> temp = new HashSet<>();
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
}
