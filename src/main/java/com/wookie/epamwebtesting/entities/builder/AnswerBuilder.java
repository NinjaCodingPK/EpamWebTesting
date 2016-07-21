package com.wookie.epamwebtesting.entities.builder;

import com.wookie.epamwebtesting.entities.Answer;

public class AnswerBuilder {
    private Answer instance;

    public AnswerBuilder() {
        instance = new Answer();
    }

    public AnswerBuilder setId(int id) {
        instance.setId(id);
        return this;
    }

    public AnswerBuilder setText(String text) {
        instance.setText(text);
        return this;
    }

    public Answer build() {
        return instance;
    }

}
