package com.wookie.epamwebtesting.entities.builder;

import com.wookie.epamwebtesting.entities.Subject;


public class SubjectBuilder {
    private Subject instance;

    public SubjectBuilder() {
        instance = new Subject();
    }

    public SubjectBuilder setId(int id) {
        instance.setId(id);
        return this;
    }

    public SubjectBuilder setName(String name) {
        instance.setName(name);
        return this;
    }

    public Subject build() {
        return instance;
    }
}
