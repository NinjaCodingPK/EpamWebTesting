/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.services;

import com.wookie.epamwebtesting.dao.DaoFactory;
import com.wookie.epamwebtesting.dao.SubjectDao;
import com.wookie.epamwebtesting.dao.TaskDao;
import com.wookie.epamwebtesting.dao.TestDao;
import com.wookie.epamwebtesting.dao.TestTasksDao;
import com.wookie.epamwebtesting.dao.UserDao;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.builder.SubjectBuilder;
import com.wookie.epamwebtesting.entities.builder.TestBuilder;
import com.wookie.epamwebtesting.entities.builder.UserBuilder;
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
        //return testDao.findBySubject(subjectDao.getId(subject));
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
     * Tests fills with subject, tutor and toughness values.
     *
     * @param input list of subject.
     * @return set of Tests.
     */
    public Map<Test, Integer> fillTests(Set<Test> input) throws RuntimeException {
        Map<Test, Integer> tests = new HashMap<>();

        Set<Test> temp = input;
        for (Test t : temp) {
            t.setSubject(subjectDao.findById(t.getSubject().getId()));
            t.setTutor(tutorDao.findById(t.getTutor().getId()));
            tests.put(t, calculateToughness(taskService.getTasks(t.getId())));
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
        test.setTasks(taskService.getTasks(id));
        test.setSubject(subjectDao.findById(test.getSubject().getId()));

        return test;
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
     * Method calculates student's result after completing of the test. 
     * @param request Http request which contains necessary parameters..
     * @return count of right answers.
     */
    public int getResult(HttpServletRequest request) throws RuntimeException {
        int res = 0;

        String[] question = request.getParameterValues("question_id");

        for (String i : question) {
            int answid = Integer.parseInt(request.getParameter("question" + i));
            if (taskService.isRightAnswer(Integer.parseInt(i), answid)) {
                res++;
            }
        }
        return res;
    }

    /**
     * Method set a subject to the test.
     * @param testId ID of test.
     * @param subject name of subject
     */
    public void setSubject(int testId, String subject) throws RuntimeException {
        Test test = testDao.findById(testId);
        test.getSubject().setId(subjectDao.getId(subject));
        testDao.update(test);
    }

    /**
     * Method delete test and all entries of this test from database.
     * @param id 
     */
    public void deleteTest(int id) throws RuntimeException {
        Set<TestTasks> testTasks = testTasksDao.findByTestId(id);
        Set<Task> tasks = new HashSet<>();

        testDao.delete(id);

        for (TestTasks t : testTasks) {
            tasks.add(taskDao.findById(t.getTaskId()));
        }

        for (Task t : tasks) {
            if (testTasksDao.findByTaskId(t.getId()).isEmpty()) {
                taskService.deleteTask(t.getId());

            }
        }
    }

    public int addTest(Integer tutorId, Integer subjectId) throws RuntimeException {
        return testDao.create(new TestBuilder()
                    .setSubject(new SubjectBuilder().setId(subjectId).build())
                    .setTutor(new UserBuilder().setId(tutorId).build())
                    .build()).getId();
    }

}
