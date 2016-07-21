package com.wookie.epamwebtesting.entities;

/**
 * Created by wookie on 6/27/16.
 */
public class TaskAnswers {
    private int taskId;
    private int answerId;
    private boolean correctness;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public boolean isCorrectness() {
        return correctness;
    }

    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }
}
