package com.wookie.epamwebtesting.entities;

import java.util.List;
import java.util.Set;

/**
 * Created by wookie on 6/27/16.
 */
public class Test {
    private int id;
    //private int tutor;
    //private int subject;
    /**
     * Set of "Task" objects. Filled every time using JdbcTestDao.
     */
    private Set<Task> tasks;
    private User tutor;
    private Subject subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getTutor() {
        return tutor;
    }

    public void setTutor(User tutor) {
        this.tutor = tutor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
