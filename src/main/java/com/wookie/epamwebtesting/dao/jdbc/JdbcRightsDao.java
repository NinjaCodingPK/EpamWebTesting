/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.dao.jdbc;

import com.wookie.epamwebtesting.dao.RightsDao;
import com.wookie.epamwebtesting.entities.Rights;
import com.wookie.epamwebtesting.entities.User;
import com.wookie.epamwebtesting.entities.builder.RightsBuilder;
import com.wookie.epamwebtesting.entities.builder.UserBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


public class JdbcRightsDao implements RightsDao {
    public static final String SEARCH_BY_ID_STATEMENT = 
            "SELECT * from Rights WHERE id=?;";
    public static final String SEARCH_BY_ROLE_STATEMENT = 
            "SELECT * from Rights";

    @Override
    public Rights create(Rights e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Rights e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rights findById(int id) {
        try (Connection cn = JdbcDaoFactory.getConnection()) {
            PreparedStatement preparedStatement = cn.prepareStatement(SEARCH_BY_ID_STATEMENT);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Rights temp = null;
            if(rs.next()) {
                temp = new RightsBuilder()
                        .setName(rs.getString("name"))
                        .setId(rs.getInt("id"))
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
    public Set<Rights> findAll() {
       try (Connection cn = JdbcDaoFactory.getConnection()) {
            Statement query = cn.createStatement();
            ResultSet rs = query.executeQuery(SEARCH_BY_ROLE_STATEMENT);
            Set<Rights> res = new HashSet<>();
            while (rs.next()) {
                res.add(new RightsBuilder()
                        .setName(rs.getString("name"))
                        .setId(rs.getInt("id"))
                        .build());
            }
            query.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
}
