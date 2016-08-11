/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.DaoFactory;
import com.wookie.epamwebtesting.dao.UserDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcDaoFactory;
import com.wookie.epamwebtesting.dao.jdbc.JdbcUserDao;
import com.wookie.epamwebtesting.entities.User;
import com.wookie.epamwebtesting.entities.builder.RightsBuilder;
import com.wookie.epamwebtesting.entities.builder.UserBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author wookie
 */
public class JdbcUserDaoTest {
    private class TestUserDao extends JdbcUserDao {
        @Override
        protected Connection getConnection() throws SQLException {
            return TestJdbcDaoFactory.getConnection(); 
        }
        
    } 
    
    private TestJdbcDaoFactory factory = new TestJdbcDaoFactory() {
        @Override
        public TestUserDao createUserDao() {
            return new TestUserDao(); //To change body of generated methods, choose Tools | Templates.
        }
        
    };
    
    private TestUserDao dao = (TestUserDao)factory.createUserDao();

    @Test
    public void testFindById() {
        User user = dao.findById(1);
        assertNotNull("Find by id", user);
    }

    @Test
    public void testFindAll() {
        Set<User> users = dao.findAll();
        assertEquals("Find all test", false, users.isEmpty());
    }

//    @Test
//    public void testCreate() {
//        User newUser = dao.create(new UserBuilder()
//                .setName("a")
//                .setSurname("b")
//                .setLogin("c")
//                .setPassword("d")
//                .setRights(new RightsBuilder().setId(2).build())
//                .build());
//        
//        assertNotNull(newUser);
//    }
//
//    @Test
//    public void testDelete() {
//        dao.delete(1);
//    }    
//    
//    @Test
//    public void testUpdate() {
//        dao.update(new UserBuilder()
//                .setId(1)
//                .setName("asd")
//                .setSurname("asdf")
//                .setLogin("asf")
//                .setPassword("asd")
//                .build());
//    }
    
}
