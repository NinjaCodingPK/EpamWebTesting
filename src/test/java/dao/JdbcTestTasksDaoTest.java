/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.TestTasksDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcTestTasksDao;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.User;
import com.wookie.epamwebtesting.entities.builder.RightsBuilder;
import com.wookie.epamwebtesting.entities.builder.TestTasksBuilder;
import com.wookie.epamwebtesting.entities.builder.UserBuilder;
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
public class JdbcTestTasksDaoTest {
    private class TestTasksDaoTest extends JdbcTestTasksDao {
        @Override
        protected Connection getConnection() throws SQLException {
            return TestJdbcDaoFactory.getConnection(); 
        }
    }
    
    private TestJdbcDaoFactory factory = new TestJdbcDaoFactory() {
        @Override
        public TestTasksDao createTestTasksDao() {
            return new TestTasksDaoTest(); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private TestTasksDaoTest dao = (TestTasksDaoTest)factory.createTestTasksDao();

    @Test
    public void testFindByTestId() {
        Set<TestTasks> instance = dao.findByTestId(1);
        assertNotNull("test find by test ID", instance);
    }
    
    @Test
    public void testFindByTaskId() {
        Set<TestTasks> instance = dao.findByTaskId(1);
        assertNotNull("test find by task ID", instance);
    }
    
    @Test
    public void testCreate() {
        TestTasks instance = dao.create(new TestTasksBuilder()
                .setTaskId(1)
                .setTestId(1)
                .build());
        
        assertNotNull(instance);
    }

    @Test
    public void testDeleteEntries() {
        boolean instance = dao.deleteEntries(1, 1);
        boolean expected = true;
        assertEquals("Delete test", expected, instance);
    }
}
