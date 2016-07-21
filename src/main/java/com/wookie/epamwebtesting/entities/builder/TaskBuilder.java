package com.wookie.epamwebtesting.entities.builder;

import com.wookie.epamwebtesting.entities.Task;


public class TaskBuilder {
    private Task instance;

    public TaskBuilder() {
        instance = new Task();
    }

    public TaskBuilder setId(int id) {
        instance.setId(id);
        return this;
    }

    public TaskBuilder setText(String text) {
        instance.setText(text);
        return this;
    }

    public TaskBuilder setToughness(int toughness) {
        instance.setToughness(toughness);
        return this;
    }

    public Task build() {
        return instance;
    }
}
