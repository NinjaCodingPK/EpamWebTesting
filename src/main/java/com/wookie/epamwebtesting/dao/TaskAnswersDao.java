package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.entities.TaskAnswers;
import java.util.Set;


public interface TaskAnswersDao extends GenericDao<TaskAnswers> {
    Set<TaskAnswers> findByTaskId(int taskId);
    Set<TaskAnswers> findByAnswerId(int answerId);
    boolean getCorrectness(int taskId, int answerId);
}
