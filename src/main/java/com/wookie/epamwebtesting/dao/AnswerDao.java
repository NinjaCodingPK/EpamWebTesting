package com.wookie.epamwebtesting.dao;

import com.wookie.epamwebtesting.entities.Answer;


public interface AnswerDao extends GenericDao<Answer> {
    Answer getByText(String text); 
}
