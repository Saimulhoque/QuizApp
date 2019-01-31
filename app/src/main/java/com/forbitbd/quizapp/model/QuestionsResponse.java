package com.forbitbd.quizapp.model;

import java.util.List;

public class QuestionsResponse {

    private int count;
    private List<Question> questions;

    public QuestionsResponse() {
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
