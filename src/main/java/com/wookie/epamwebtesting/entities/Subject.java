package com.wookie.epamwebtesting.entities;

/**
 * Created by wookie on 6/27/16.
 */
public class Subject {
    private int id;
    private String name;



//    public Subject(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Subject(String name) {
//        this.name = name;
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
}
