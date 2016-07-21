package com.wookie.epamwebtesting.entities;

/**
 * Created by wookie on 6/27/16.
 */
public class Answer {

    private int id;
    private String text;

    public Answer() {

    }

    public Answer(int id, String text) {
        this.id = id;
        this.text = text;
    }
//
//    public Answer(String text) {
//        //this.id = id;
//        this.text = text;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
