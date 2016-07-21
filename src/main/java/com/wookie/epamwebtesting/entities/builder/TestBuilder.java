package com.wookie.epamwebtesting.entities.builder;

import com.wookie.epamwebtesting.entities.Subject;
import com.wookie.epamwebtesting.entities.Task;
import com.wookie.epamwebtesting.entities.Test;
import com.wookie.epamwebtesting.entities.User;
import java.util.List;
import java.util.Set;


public class TestBuilder {
    private Test instance;

    public TestBuilder() {
        instance = new Test();
    }

    public TestBuilder setId(int id) {
        instance.setId(id);
        return this;
    }

    public TestBuilder setTutor(User tutor) {
        instance.setTutor(tutor);
        return this;
    }

    public TestBuilder setSubject(Subject subject) {
        instance.setSubject(subject);
        return this;
    }

    public TestBuilder setTasks(Set<Task> tasks) {
        instance.setTasks(tasks);
        return this;
    }
    
    public Test build() {
        return instance;
    }
}
