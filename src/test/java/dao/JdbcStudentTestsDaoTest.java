/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.wookie.epamwebtesting.dao.StudentTestsDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcStudentTestsDao;
import com.wookie.epamwebtesting.entities.StudentTests;
import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.entities.builder.StudentTestsBuilder;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
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
public class JdbcStudentTestsDaoTest {
    private class DaoTest extends JdbcStudentTestsDao {
        @Override
        protected Connection getConnection() throws SQLException {
            return TestJdbcDaoFactory.getConnection(); 
        }
    }
    
    private TestJdbcDaoFactory factory = new TestJdbcDaoFactory() {
        @Override
        public StudentTestsDao createStudentTestsDao() {
            return new DaoTest(); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private DaoTest dao = (DaoTest)factory.createStudentTestsDao();

    @Test
    public void tsetGetByStudent() {
        Set<StudentTests> instance = dao.getByStudent(1);
        assertNotNull("Find by student's id", instance);
    }
    
    @Test
    public void testGetResult() {
        Integer instance = dao.getResult(1, 1);
        Integer expected = 10;
        assertEquals("Get result test", expected, instance);
    }

//    @Test
//    public void testCreate() {
//        StudentTests instance = dao.create(new StudentTestsBuilder()
//                .setStudentId(1)
//                .setTestId(1)
//                .setResult(10)
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
//        dao.update(new StudentTestsBuilder()
//                .setStudentId(1)
//                .setTestId(1)
//                .setResult(10)
//                .build());
//    }
    
}
