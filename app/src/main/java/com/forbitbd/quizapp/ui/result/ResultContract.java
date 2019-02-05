package com.forbitbd.quizapp.ui.result;

import com.forbitbd.quizapp.model.Question;

import java.util.List;

public interface ResultContract {

    interface Presenter{
        void renderResult(List<Question> questionList);
    }

    interface View{
        void renderTotalTextView(String value);
        void renderAnsweredTextView(String value);
        void renderLivedTextView(String value);
        void renderCorrectAnswerTextView(String value);
    }
}
