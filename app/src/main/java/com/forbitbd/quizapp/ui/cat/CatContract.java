package com.forbitbd.quizapp.ui.cat;

import com.forbitbd.quizapp.model.Question;
import com.forbitbd.quizapp.model.Result;

import java.util.List;

public interface CatContract {

    interface Presenter{
        void getAllQuestions(String catName,String subcat);
        void postResultToDatabase(String catName, String subcat, List<Result> results);
    }

    interface View{
        void initialize(List<Question> questionList);
        void startLoginActivity();
        void showDialog();
        void startResultActivity();
    }


}
