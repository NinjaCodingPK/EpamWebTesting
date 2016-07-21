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
import com.wookie.epamwebtesting.entities.TaskAnswers;
import com.wookie.epamwebtesting.entities.builder.TaskAnswersBuilder;


public class AnswerService {
    private TaskAnswersDao taskAnswersDao = DaoFactory.getFactory().createTaskAnswersDao();
    private AnswerDao answerDao = DaoFactory.getFactory().createAnswerDao();
    
    private static AnswerService instance = new AnswerService();
    
    public static AnswerService getInstance() {
        return instance;
    }
    
    private Answer createAnswer(Answer answer) throws RuntimeException {
        Answer temp = answerDao.getByText(answer.getText());
        if(temp == null) {
            temp = answerDao.create(answer);
            //temp = answerDao.getByText(answer.getText());
        }
        
        return temp;
    }
    
//    public Answer updateAnswer(Answer answer) {
//        if(taskAnswersDao.findByAnswerId(answer.getId()).size() > 1) {
//            return createAnswer(answer);
//        }
//        else {
//            return null; 
//        }
//        
//    }
    
    public void addAnswer(Answer answer, int taskId, boolean correctness) throws RuntimeException {
        Answer temp = createAnswer(answer);
        //try{ //or maybe just throw this. 
            taskAnswersDao.create(new TaskAnswersBuilder()
                        .setAnswerId(temp.getId())
                        .setTaskId(taskId)
                        .setCorrectness(correctness)
                        .build());
//        }
//        catch(RuntimeException e) {
//            
//        }
    }
}
