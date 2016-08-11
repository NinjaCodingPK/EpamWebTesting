/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.RightsDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcRightsDao;
import com.wookie.epamwebtesting.entities.Rights;
import com.wookie.epamwebtesting.entities.StudentTests;
import com.wookie.epamwebtesting.entities.builder.RightsBuilder;
import com.wookie.epamwebtesting.entities.builder.StudentTestsBuilder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author wookie
 */
public class JdbcRightsDaoTest {
    private class DaoTest extends JdbcRightsDao {
        @Override
        protected Connection getConnection() throws SQLException {
            return TestJdbcDaoFactory.getConnection(); 
        }
    }
    
    private TestJdbcDaoFactory factory = new TestJdbcDaoFactory() {
        @Override
        public RightsDao createRightsDao() {
            return new DaoTest(); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private DaoTest dao = (DaoTest)factory.createRightsDao();

    @Test
    public void testFindById() {
        Rights instance = dao.findById(1);
        assertNotNull("Find by id", instance);
    }

    @Test
    public void testFindAll() {
        Set<Rights> instance = dao.findAll();
        assertEquals("Find all test", false, instance.isEmpty());
    }

//    @Test
//    public void testCreate() {
//        Rights instance = dao.create(new RightsBuilder()
//                .setName("right")
//                .build());
//        
//        assertNotNull(instance);
//    }
//
//    @Test
//    public void testDelete() {
//        dao.delete(1);
//    }
//    
//    @Test
//    public void testUpdate() {
//        dao.update(new RightsBuilder()
//                .setId(1)
//                .setName("test")
//                .build());
//    }
    
}
