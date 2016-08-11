/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.services;

import com.wookie.epamwebtesting.dao.AnswerDao;
import com.wookie.epamwebtesting.dao.DaoFactory;
import com.wookie.epamwebtesting.dao.TaskAnswersDao;
import com.wookie.epamwebtesting.entities.Answer;
import com.wookie.epamwebtesting.entities.builder.TaskAnswersBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AnswerService {
    private static final Logger logger = LogManager.getLogger(AnswerService.class);
    private TaskAnswersDao taskAnswersDao = DaoFactory.getFactory().createTaskAnswersDao();
    private AnswerDao answerDao = DaoFactory.getFactory().createAnswerDao();
    
    private static AnswerService instance = new AnswerService();
    
    public static AnswerService getInstance() {
        return instance;
    }
    
    /**
     * Add an answer in database if such answer haven't already exist.
     * @param answer Answer instance.
     * @throws RuntimeException if some trouble with database appears. 
     */
    private Answer createAnswer(Answer answer) throws RuntimeException {
        Answer temp = answerDao.getByText(answer.getText());
        if(temp == null) {
            temp = answerDao.create(answer);
        }
        
        return temp;
    }
    
    /**
     * Add an answer and answer's connection  with task in database if such answer haven't already exist.
     * @param answer Answer instance.
     * @param taskId ID of answer's task.
     * @param correctness true if current answer is correct. False - if not.
     * @throws RuntimeException 
     */
    public void addAnswer(Answer answer, int taskId, boolean correctness) throws RuntimeException {
        Answer temp = createAnswer(answer);
        //try { 
            taskAnswersDao.create(new TaskAnswersBuilder()
                        .setAnswerId(temp.getId())
                        .setTaskId(taskId)
                        .setCorrectness(correctness)
                        .build());
//        }
//        catch(RuntimeException e) {
//            logger.warning("Error while processing database " + e);
//        }
    }
}
