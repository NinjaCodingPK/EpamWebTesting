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
import com.wookie.epamwebtesting.entities.Answer;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.TaskAnswers;
import com.wookie.epamwebtesting.entities.TestTasks;
import com.wookie.epamwebtesting.entities.builder.AnswerBuilder;
import com.wookie.epamwebtesting.entities.builder.TaskBuilder;
import com.wookie.epamwebtesting.entities.builder.TestTasksBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TaskService {

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
//        TaskDao taskDao = DaoFactory.getFactory().createTaskDao();
//        AnswerDao answerDao = DaoFactory.getFactory().createAnswerDao();
//        TaskAnswersDao taskAnswersDao = DaoFactory.getFactory().createTaskAnswersDao();
//        TestTasksDao testTasksDao = DaoFactory.getFactory().createTestTasksDao();

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

//        Task task = new Task();
//        task.setId(0);
//        task.setText("EWfwfwe?");
//        task.setToughness(3);
        return taskDao.findById(id);
    }

    public boolean isRightAnswer(int taskId, int answerId) throws RuntimeException {
//        TaskAnswersDao taskAnswersDao = DaoFactory.getFactory().createTaskAnswersDao();
        return taskAnswersDao.getCorrectness(taskId, answerId);
    }

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
        //return (String[])answers.toArray();
    }

    private Task createTask(Task task)throws RuntimeException {
        Task temp = taskDao.getByText(task.getText());
        if (temp == null) {
            temp = taskDao.create(task);
            //temp = taskDao.getByText(task.getText());
        }

        return temp;
    }

    private Task updateTask(Task task) throws RuntimeException {
        if (taskAnswersDao.findByTaskId(task.getId()).size() > 1) {
            deleteTask(task.getId());
            return createTask(task);
        } else {
            taskDao.update(task);
            return task;
        }
    }

    public boolean addTask(Integer taskId, Integer testId, String question, String[] answers, Integer toughness) throws RuntimeException { 
        Task task;
        if (taskId.equals(0)) {
            task = createTask(new TaskBuilder()
                    .setText(question)
                    .setToughness(toughness)
                    .build());
        } else {
            task = updateTask(new TaskBuilder()
                    .setId(taskId)
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

//        try { // Or maybe just throw this.
            testTasksDao.create(new TestTasksBuilder()
                    .setTaskId(task.getId())
                    .setTestId(testId)
                    .build());
//        } catch (RuntimeException e) {
//
//        }

        return true;
    }

    public void deleteTask(int id) throws RuntimeException {
        Set<TaskAnswers> taskAnswers = taskAnswersDao.findByTaskId(id);
        Set<Answer> answers = new HashSet<>();
        for (TaskAnswers t : taskAnswers) {
            answers.add(answerDao.findById(t.getAnswerId()));
        }

        taskDao.delete(id);

        for (Answer a : answers) {
            if (taskAnswersDao.findByAnswerId(a.getId()).isEmpty()) {
                answerDao.delete(a.getId());
            }
        }
    }
}
