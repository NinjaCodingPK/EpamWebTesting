package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.entities.TaskAnswers;
import com.wookie.epamwebtesting.entities.TestTasks;
import java.util.Set;


public interface TestTasksDao extends GenericDao<TestTasks> {
     public Set<TestTasks> findByTestId(int testId);
     public Set<TestTasks> findByTaskId(int taskId);
}
