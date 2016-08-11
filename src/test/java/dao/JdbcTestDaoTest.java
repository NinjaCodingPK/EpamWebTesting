/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.TestDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcTestDao;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
import com.wookie.epamwebtesting.entities.builder.TestBuilder;
import com.wookie.epamwebtesting.entities.builder.TestTasksBuilder;
import com.wookie.epamwebtesting.entities.builder.UserBuilder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author wookie
 */
public class JdbcTestDaoTest {
    private class DaoTest extends JdbcTestDao {
        @Override
        protected Connection getConnection() throws SQLException {
            return TestJdbcDaoFactory.getConnection(); 
        }
    }
    
    private TestJdbcDaoFactory factory = new TestJdbcDaoFactory() {
        @Override
        public TestDao createTestDao() {
            return new DaoTest(); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private DaoTest dao = (DaoTest)factory.createTestDao();

    @org.junit.Test
    public void testFindById() {
        Test instance = dao.findById(1);
        assertNotNull("Find by id", instance);
    }

    @org.junit.Test
    public void testFindAll() {
        Set<Test> instance = dao.findAll();
        assertEquals("Find all test", false, instance.isEmpty());
    }

    @org.junit.Test
    public void testCreate() {
        Test instance = dao.create(new TestBuilder()
                .setSubject(new SubjectBuilder().setId(1).build())
                .setTutor(new UserBuilder().setId(1).build())
                .build());
        
        assertNotNull(instance);
    }

    @org.junit.Test
    public void testDelete() {
        dao.delete(1);
    }
    
    @org.junit.Test
    public void testUpdate() {
        dao.update(new TestBuilder()
                .setId(1)
                .setSubject(new SubjectBuilder().setId(1).build())
                .setTutor(new UserBuilder().setId(1).build())
                .build());
    }
    
}
