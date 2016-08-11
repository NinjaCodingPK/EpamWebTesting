/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.SubjectDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcSubjectDao;
import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.entities.TaskAnswers;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
import com.wookie.epamwebtesting.entities.builder.TaskAnswersBuilder;
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
public class JdbcSubjectDaoTest {
    private class DaoTest extends JdbcSubjectDao {
        @Override
        protected Connection getConnection() throws SQLException {
            return TestJdbcDaoFactory.getConnection(); 
        }
    }
    
    private TestJdbcDaoFactory factory = new TestJdbcDaoFactory() {
        @Override
        public SubjectDao createSubjectDao() {
            return new DaoTest(); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private DaoTest dao = (DaoTest)factory.createSubjectDao();
    
    @Test
    public void testFindById() {
        Subject instance = dao.findById(1);
        assertNotNull("Find by id", instance);
    }

    @Test
    public void testFindAll() {
        Set<Subject> instance = dao.findAll();
        assertEquals("Find all test", false, instance.isEmpty());
    }

//    @Test
//    public void testCreate() {
//        Subject instance = dao.create(new SubjectBuilder()
//                .setName("subject")
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
//        dao.update(new SubjectBuilder()
//                .setId(1)
//                .setName("test name")
//                .build());
//    }
}
