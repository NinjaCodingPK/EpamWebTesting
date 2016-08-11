/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.TaskAnswersDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcTaskAnswersDao;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.TaskAnswers;
import com.wookie.epamwebtesting.entities.builder.TaskAnswersBuilder;
import com.wookie.epamwebtesting.entities.builder.TaskBuilder;
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
public class JdbcTaskAnswersDaoTest {
    private class DaoTest extends JdbcTaskAnswersDao {
        @Override
        protected Connection getConnection() throws SQLException {
            return TestJdbcDaoFactory.getConnection(); 
        }
    }
    
    private TestJdbcDaoFactory factory = new TestJdbcDaoFactory() {
        @Override
        public TaskAnswersDao createTaskAnswersDao() {
            return new DaoTest(); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private DaoTest dao = (DaoTest)factory.createTaskAnswersDao();

    @Test
    public void testFindById() {
        TaskAnswers instance = dao.findById(1);
        assertNotNull("Find by id", instance);
    }

    @Test
    public void testFindByTaskId() {
        Set<TaskAnswers> instance = dao.findByTaskId(1);
        assertNotNull("Find by task id", instance);
    }
    
    @Test
    public void testFindByAnswerId() {
        Set<TaskAnswers> instance = dao.findByAnswerId(1);
        assertNotNull("Find by answer id", instance);
    }
    
    @Test
    public void testGetCorrectness() {
        boolean instance = dao.getCorrectness(1, 1);
        assertEquals("Get correctness test", true, instance);
    }
    
//    @Test
//    public void testCreate() {
//        TaskAnswers instance = dao.create(new TaskAnswersBuilder()
//                .setAnswerId(1)
//                .setTaskId(1)
//                .setCorrectness(true)
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
//        dao.update(new TaskAnswersBuilder()
//                .setTaskId(1)
//                .setAnswerId(1)
//                .setCorrectness(true)
//                .build());
//    }
}
