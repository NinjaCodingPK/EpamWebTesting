package com.wookie.epamwebtesting.entities.builder;

import com.wookie.epamwebtesting.entities.Rights;
import com.wookie.epamwebtesting.entities.User;


public class UserBuilder {
    private User instance;

    public UserBuilder() {
        instance = new User();
    }

    public UserBuilder setId(int id) {
        instance.setId(id);
        return this;
    }

    public UserBuilder setName(String name) {
        instance.setName(name);
        return this;
    }

    public UserBuilder setSurname(String surname) {
        instance.setSurname(surname);
        return this;
    }

    public UserBuilder setLogin(String login) {
        instance.setLogin(login);
        return this;
    }

    public UserBuilder setPassword(String password) {
        instance.setPassword(password);
        return this;
    }
    
    public UserBuilder setRights(Rights rights) {
        instance.setRights(rights);
        return this;
    }

    public User build() {
        return instance;
    }
}
