package com.forbitbd.quizapp.model;

import java.io.Serializable;

public class Question implements Serializable {

    private String _id;
    private String question;
    private String category;
    private String[] options;
    private int answer;
    private int user_answer=-1;


    public Question() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(int user_answer) {
        this.user_answer = user_answer;
    }

    public Result getResult(){
        return new Result(this._id,this.user_answer);
    }
}
