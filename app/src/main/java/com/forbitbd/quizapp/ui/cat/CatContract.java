package com.forbitbd.quizapp.ui.cat;

import com.forbitbd.quizapp.model.Question;

import java.util.List;

public interface CatContract {

    interface Presenter{
        void getAllQuestions(String catName);
    }

    interface View{
        void initialize(List<Question> questionList);
    }


}
