package com.wookie.epamwebtesting.entities.builder;

import com.wookie.epamwebtesting.entities.TestTasks;


public class TestTasksBuilder {
    private TestTasks instance;

    public TestTasksBuilder() {
        instance = new TestTasks();
    }

    public TestTasksBuilder setTaskId(int taskId) {
        instance.setTaskId(taskId);
        return this;
    }

    public TestTasksBuilder setTestId(int testId) {
        instance.setTestId(testId);
        return this;
    }

    public TestTasks build() {
        return instance;
    }
}
