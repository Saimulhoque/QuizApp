package com.forbitbd.quizapp.ui.cat;

import com.forbitbd.quizapp.model.Question;

import java.util.List;

public interface CatContract {

    interface Presenter{
        void getAllQuestions(String catName,String subcat);
    }

    interface View{
        void initialize(List<Question> questionList);
        void startLoginActivity();
    }


}
