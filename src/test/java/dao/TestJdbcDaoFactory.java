/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.jdbc.JdbcDaoFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wookie
 */
public class TestJdbcDaoFactory extends JdbcDaoFactory{
     public static final String HOST = "jdbc:mysql://localhost:3306/testingTest"
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC&useSSL=false";
        public static final String ROOT = "root";
        public static final String PASSWORD = "apocalypse";
    
        static Connection getConnection() throws SQLException {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection(HOST , ROOT , PASSWORD) ;
        }
}
