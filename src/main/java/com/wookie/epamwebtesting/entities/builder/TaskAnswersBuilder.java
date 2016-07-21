package com.wookie.epamwebtesting.entities.builder;

import com.wookie.epamwebtesting.entities.TaskAnswers;


public class TaskAnswersBuilder {
    private TaskAnswers instance;


    public TaskAnswersBuilder() {
        instance = new TaskAnswers();
    }

    public TaskAnswersBuilder setTaskId(int taskId) {
        instance.setTaskId(taskId);
        return this;
    }

    public TaskAnswersBuilder setAnswerId(int answerId) {
        instance.setAnswerId(answerId);
        return this;
    }

    public TaskAnswersBuilder setCorrectness(boolean correctness) {
        instance.setCorrectness(correctness);
        return this;
    }

    public TaskAnswers build() {
        return instance;
    }
}
