/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.services;

import com.wookie.epamwebtesting.controllers.constants.Constants;
import com.wookie.epamwebtesting.dao.DaoFactory;
import com.wookie.epamwebtesting.dao.StudentTestsDao;
import com.wookie.epamwebtesting.dao.SubjectDao;
import com.wookie.epamwebtesting.dao.TaskDao;
import com.wookie.epamwebtesting.dao.TestDao;
import com.wookie.epamwebtesting.dao.TestTasksDao;
import com.wookie.epamwebtesting.dao.UserDao;
import com.wookie.epamwebtesting.entities.StudentTests;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.User;
import com.wookie.epamwebtesting.entities.builder.StudentTestsBuilder;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
import com.wookie.epamwebtesting.entities.builder.TestBuilder;
import com.wookie.epamwebtesting.entities.builder.UserBuilder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;


public class TestService {
    private TestDao testDao = DaoFactory.getFactory().createTestDao();
    private TaskDao taskDao = DaoFactory.getFactory().createTaskDao();
    private TestTasksDao testTasksDao = DaoFactory.getFactory().createTestTasksDao();
    private UserDao tutorDao = DaoFactory.getFactory().createUserDao();
    private SubjectDao subjectDao = DaoFactory.getFactory().createSubjectDao();
    private TaskService taskService = TaskService.getInstance();
    private StudentTestsDao studentTestsDao = DaoFactory.getFactory().createStudentTestsDao();

    private static TestService instance = new TestService();

    public static TestService getInstance() {
        return instance;
    }

    /**
     * Find tests by subject.
     *
     * @param subject name of subject.
     * @return set of Tests.
     */
    public Set<Test> getBySubject(int subject) throws RuntimeException {
        return testDao.findBySubject(subject);
    }

    /**
     * Find tests by user.
     *
     * @param userId id of user.
     * @return set of Tests.
     */
    public Set<Test> getByUser(int userId) throws RuntimeException {
        return testDao.findByUser(userId);
    }

    /**
     * Tests fills with subject, tutor and tasks values
     * @param test Test for forming.
     * @return formed test.
     */
    private Test formTest(Test test) {
        test.setTasks(taskService.getTasks(test.getId()));
        test.setSubject(subjectDao.findById(test.getSubject().getId()));
        test.setTutor(tutorDao.findById(test.getTutor().getId()));

        return test;
    }
    
    /**
     * Tests fills with subject, tutor and toughness values.
     *
     * @param input list of subject.
     * @return set of Tests.
     */
    public Map<Test, Integer> fillTests(Set<Test> input) throws RuntimeException {
        Map<Test, Integer> tests = new HashMap<>();

        Set<Test> temp = input;
        Test formedTest;
        for (Test t : temp) {
            formedTest = formTest(t);
            tests.put(formedTest, calculateToughness(taskService.getTasks(formedTest.getId())));
        }
        return tests;
    }

    /**
     * Method finds test by id. Test fills with tasks.
     *
     * @param id test's ID.
     * @return "Test" object.
     */
    public Test getTest(int id) throws RuntimeException {
        Test test = testDao.findById(id);

        return formTest(test);
    }

    /**
     * Method calculates toughness of test.
     * @param tasks test's tasks.
     * @return summed toughness.
     */
    private int calculateToughness(Set<Task> tasks) {
        int result = 0;

        for (Task t : tasks) {
            result += t.getToughness();
        }

        return result;
    }

    /**
     * Method connect user with passed test.
     * @param userId ID of a user.
     * @param testId ID of test.
     * @param result result value.
     */
    public void setPassedTest(int userId, int testId, int result) {
        StudentTests studentTests = new StudentTestsBuilder()
                .setStudentId(userId)
                .setTestId(testId)
                .setResult(result)
                .build();
        try {
            studentTestsDao.create(studentTests);
        } catch (RuntimeException e) {
            studentTestsDao.update(studentTests);
        }
    }
    
    /**
     * Method calculates student's result after completing of the test. 
     * @param request Http request which contains necessary parameters.
     * @return count of right answers.
     */
    public int getResult(HttpServletRequest request) throws RuntimeException {
        int res = 0;

        String[] question = request.getParameterValues(Constants.PROPERTY_TASK_QUESTION_ID);

        for (String i : question) {
            int answid = Integer.parseInt(request.getParameter(Constants.PROPERTY_TASK_QUESTION + i));
            if (taskService.isRightAnswer(Integer.parseInt(i), answid)) {
                res++;
            }
        }
        
        User user = (User)request.getSession().getAttribute(Constants.USER_SESSION_ATTRIBUTE);
        setPassedTest(user.getId(),
                Integer.parseInt(request.getParameter(Constants.PROPERTY_TEST_ID)),
                res);
        
        return res;
    }

    /**
     * Method set a subject to the test.
     * @param testId ID of test.
     * @param subject name of subject.
     */
    public void setSubject(int testId, String subject) throws RuntimeException {
        Test test = testDao.findById(testId);
        test.getSubject().setId(subjectDao.getId(subject));
        testDao.update(test);
    }

    /**
     * Method delete test and all entries of this test from database.
     * @param id test ID.
     */
    public void deleteTest(int id) throws RuntimeException {
        Set<TestTasks> testTasks = testTasksDao.findByTestId(id);
        Set<Task> tasks = new HashSet<>();

        studentTestsDao.delete(id);
        testDao.delete(id);

        for (TestTasks t : testTasks) {
            tasks.add(taskDao.findById(t.getTaskId()));
        }

        for (Task t : tasks) {
            if (testTasksDao.findByTaskId(t.getId()).isEmpty()) {
                taskService.deleteTask(t.getId(), id);

            }
        }
    }

    /**
     * Method creates new test in database.
     * @param tutorId ID of a tutor.
     * @param subjectId ID of a subject.
     * @return ID of created test.
     * @throws RuntimeException 
     */
    public int addTest(Integer tutorId, Integer subjectId) throws RuntimeException {
        return testDao.create(new TestBuilder()
                    .setSubject(new SubjectBuilder().setId(subjectId).build())
                    .setTutor(new UserBuilder().setId(tutorId).build())
                    .build()).getId();
    }

    public Map<Test, Integer> getStudentTests(int studentId) {
        Set<StudentTests> studentTests = studentTestsDao.getByStudent(studentId);
        Map<Test, Integer> tests = new HashMap<>();
        
        for(StudentTests s : studentTests) {
            tests.put(this.getTest(s.getTestId()),
                    studentTestsDao.getResult(studentId, s.getTestId()));
        }
        
        return tests;
    }
}
