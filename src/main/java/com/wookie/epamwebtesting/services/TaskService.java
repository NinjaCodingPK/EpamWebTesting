/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.services;

import com.wookie.epamwebtesting.dao.AnswerDao;
import com.wookie.epamwebtesting.dao.DaoFactory;
import com.wookie.epamwebtesting.dao.TaskAnswersDao;
import com.wookie.epamwebtesting.dao.TaskDao;
import com.wookie.epamwebtesting.dao.TestDao;
import com.wookie.epamwebtesting.dao.TestTasksDao;
import com.wookie.epamwebtesting.dao.jdbc.JdbcStudentTestsDao;
import com.wookie.epamwebtesting.entities.Answer;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.TaskAnswers;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.builder.AnswerBuilder;
import com.wookie.epamwebtesting.entities.builder.TaskBuilder;
import com.wookie.epamwebtesting.entities.builder.TestTasksBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TaskService {
    private static final Logger logger = LogManager.getLogger(TaskService.class);  
    private TestDao testDao = DaoFactory.getFactory().createTestDao();
    private TaskDao taskDao = DaoFactory.getFactory().createTaskDao();
    private AnswerDao answerDao = DaoFactory.getFactory().createAnswerDao();
    private TaskAnswersDao taskAnswersDao = DaoFactory.getFactory().createTaskAnswersDao();
    private TestTasksDao testTasksDao = DaoFactory.getFactory().createTestTasksDao();

    private static TaskService instance = new TaskService();

    public static TaskService getInstance() {
        return instance;
    }

    /**
     * Finds all tasks by Test's ID.
     *
     * @param testId test's ID.
     * @return Set of tasks.
     */
    public Set<Task> getTasks(int testId) throws RuntimeException {
        Set<TestTasks> testTasks = testTasksDao.findByTestId(testId);
        Set<Task> tasks = new HashSet<>();
        for (TestTasks t : testTasks) {
            tasks.add(taskDao.findById(t.getTaskId()));
        }

        for (Task t : tasks) {
            Set<TaskAnswers> taskAnswers = taskAnswersDao.findByTaskId(t.getId());
            for (TaskAnswers a : taskAnswers) {
                t.getAnswers().add(answerDao.findById(a.getAnswerId()));
            }
        }

        return tasks;
    }

    public Task getTask(int id) throws RuntimeException {
        return taskDao.findById(id);
    }

    /**
     * Method checks answer correctness.
     * @param taskId ID of a task.
     * @param answerId ID of an answer.
     * @return True if answer is correct. False - if not.
     * @throws RuntimeException 
     */
    public boolean isRightAnswer(int taskId, int answerId) throws RuntimeException {
        return taskAnswersDao.getCorrectness(taskId, answerId);
    }

    /**
     * Method gets all task answers by task ID.
     * @param taskId ID of a task.
     * @return List of answers.
     * @throws RuntimeException 
     */
    public List<String> getAnswersForTask(int taskId) throws RuntimeException {
        List<String> answers = new ArrayList<>();
        Set<TaskAnswers> taskAnswers = taskAnswersDao.findByTaskId(taskId);
        
        for(TaskAnswers t : taskAnswers) {
            Answer temp = answerDao.findById(t.getAnswerId());
            
            if(t.isCorrectness())
                answers.add(0, temp.getText());
            else
                answers.add(temp.getText());
        }
        
        return answers;
    }

    /**
     * Method creates Task in database if such task haven't already exist. 
     * @param task 
     * @return
     * @throws RuntimeException 
     */
    private Task createTask(Task task)throws RuntimeException {
        Task temp = taskDao.getByText(task.getText());
        if (temp == null) {
            temp = taskDao.create(task);
        }

        return temp;
    }

    /**
     * Method adds task in database and connects it with test and answers.
     * @param taskId ID of task.
     * @param testId ID of test.
     * @param question task's question.
     * @param answers String array - task's answers. 
     * @param toughness task's toughness.
     * @throws RuntimeException 
     */
    public void addTask(Integer taskId, Integer testId, String question, String[] answers, Integer toughness) 
            throws RuntimeException { 
        
        Task task = null;
        int currentTestId = testId;
        if (taskId.equals(0)) {
            task = createTask(new TaskBuilder()
                    .setText(question)
                    .setToughness(toughness)
                    .build());
        }

        AnswerService answerService = AnswerService.getInstance();
        for (int i = 0; i < answers.length; i++) {
            if (i == 0) {
                answerService.addAnswer(new AnswerBuilder().setText(answers[i]).build(), task.getId(), true);
            } else {
                answerService.addAnswer(new AnswerBuilder().setText(answers[i]).build(), task.getId(), false);
            }
        }

        try { 
            testTasksDao.create(new TestTasksBuilder()
                    .setTaskId(task.getId())
                    .setTestId(currentTestId)
                    .build());
        } catch (RuntimeException e) {
            logger.error("Error while processing database " + e);
        }

    }

    
    /**
     * Method deletes Task and all unused entries which are connected 
     * with this task.
     * @param taskId ID of a task.
     * @param testId ID of a test.
     * @throws RuntimeException 
     */
    public void deleteTask(int taskId, int testId) throws RuntimeException {
        testTasksDao.deleteEntries(testId, taskId);
        
        // answers which are used in current test.
        Set<TaskAnswers> taskAnswers = taskAnswersDao.findByTaskId(taskId); 
        Set<TestTasks> testTasks = testTasksDao.findByTaskId(taskId); // tests which use current task.
        Set<Answer> answers = new HashSet<>();
        Set<Test> tests = new HashSet<>();
        
        for (TaskAnswers t : taskAnswers) {
            answers.add(answerDao.findById(t.getAnswerId()));
        }
        
        for(TestTasks t : testTasks) {
            tests.add(testDao.findById(t.getTestId()));
        }
        
        if(tests.isEmpty())
            taskDao.delete(taskId);

        for (Answer a : answers) {
            if (taskAnswersDao.findByAnswerId(a.getId()).isEmpty()) {
                answerDao.delete(a.getId());
            }
        }
    }
}
