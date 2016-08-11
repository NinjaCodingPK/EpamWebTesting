/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.TaskDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcTaskDao;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.builder.TaskBuilder;
import com.wookie.epamwebtesting.entities.builder.TestTasksBuilder;
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
public class JdbcTaskDaoTest {
    private class DaoTest extends JdbcTaskDao {
        @Override
        protected Connection getConnection() throws SQLException {
            return TestJdbcDaoFactory.getConnection(); 
        }
    }
    
    private TestJdbcDaoFactory factory = new TestJdbcDaoFactory() {
        @Override
        public TaskDao createTaskDao() {
            return new DaoTest(); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private DaoTest dao = (DaoTest)factory.createTaskDao();

    @Test
    public void testFindById() {
        Task instance = dao.findById(1);
        assertNotNull("Find by id", instance);
    }

    @Test
    public void testFindAll() {
        Set<Task> instance = dao.findAll();
        assertNotNull("Find all test", instance);
    }

    @Test
    public void testGetByText() {
        Task instance = dao.getByText("2 + 2 = ?"); 
        assertNotNull("Find get by text test", instance);
    }
    
//    @Test
//    public void testCreate() {
//        Task instance = dao.create(new TaskBuilder()
//                .setText("test qeustion")
//                .setToughness(1)
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
//        dao.update(new TaskBuilder()
//                .setId(1)
//                .setText("test qeusetion")
//                .setToughness(1)
//                .build());
//    }
    
}
