/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.AnswerDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcAnswerDao;
import com.wookie.epamwebtesting.entities.Answer;
import com.wookie.epamwebtesting.entities.Rights;
import com.wookie.epamwebtesting.entities.builder.AnswerBuilder;
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
public class JdbcAnswerDaoTest {
    private class DaoTest extends JdbcAnswerDao {
        @Override
        protected Connection getConnection() throws SQLException {
            return TestJdbcDaoFactory.getConnection(); 
        }
    }
    
    private TestJdbcDaoFactory factory = new TestJdbcDaoFactory() {
        @Override
        public AnswerDao createAnswerDao() {
            return new DaoTest(); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private DaoTest dao = (DaoTest)factory.createAnswerDao();

    @Test
    public void testFindById() {
        Answer instance = dao.findById(1);
        assertNotNull("Find by id", instance);
    }

    @Test
    public void testFindAll() {
        Set<Answer> instance = dao.findAll();
        assertEquals("Find all test", false, instance.isEmpty());
    }

    //    @Test
//    public void testDelete() {
//        tutorDao.delete(1);
//    }
    
//    @Test
//    public void testCreate() {
//        dao.create(new AnswerBuilder()
//                .setText("test")
//                .build());
//    }
//    
//    @Test
//    public void testUpdate() {
//        dao.update(new AnswerBuilder()
//                .setId(1)
//                .setText("updated")
//                .build());
//    }
    
}
