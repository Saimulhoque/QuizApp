package com.forbitbd.quizapp.ui.cat.question;

public interface QuestionContract {

    interface Presenter{
        void performAction(int buttonId);
    }

    interface View{
        void performAction(int buttonId);
    }
}
