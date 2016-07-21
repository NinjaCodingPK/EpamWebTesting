package com.wookie.epamwebtesting.entities;

/**
 * Created by wookie on 6/27/16.
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Rights rights;

//    public Tutor(int id, String name, String surname, String login, String password) {
//        this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.login = login;
//        this.password = password;
//    }
//
//    public Tutor(String name, String surname, String login, String password) {
//        //this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.login = login;
//        this.password = password;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }
    
    
}
