package com.wookie.epamwebtesting.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wookie on 6/27/16.
 */
public class Task {
    private int id;
    private String text;
    private int toughness;
    /**
     * List of "Answer" object. Filled every time using JdbcTaskDao.
     */
    private Set<Answer> answers;

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

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public Set<Answer> getAnswers() {
        if(answers == null)
            answers = new HashSet<>();
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

}
