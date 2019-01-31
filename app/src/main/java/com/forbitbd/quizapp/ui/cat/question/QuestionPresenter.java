package com.forbitbd.quizapp.ui.cat.question;

public class QuestionPresenter implements QuestionContract.Presenter {

    private QuestionContract.View mView;

    public QuestionPresenter(QuestionContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void performAction(int buttonId) {
        mView.performAction(buttonId);
    }
}
