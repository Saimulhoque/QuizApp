package com.forbitbd.quizapp.ui.result;

import com.forbitbd.quizapp.model.Question;

import java.util.List;

public class ResultPresenter implements ResultContract.Presenter {

    private ResultContract.View mView;

    public ResultPresenter(ResultContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void renderResult(List<Question> questionList) {
        int total = questionList.size();
        int answered = answeredQuestions(questionList);
        int correctAnswer = getCorrectAnswer(questionList);
        mView.renderTotalTextView(String.valueOf(total)+" Nos");
        mView.renderAnsweredTextView(String.valueOf(answered)+" Nos");
        mView.renderLivedTextView(String.valueOf(total-answered)+" Nos");
        mView.renderCorrectAnswerTextView(String.valueOf(correctAnswer)+" Nos");

    }

    private int answeredQuestions(List<Question> questionList){
        int answerd = 0;

        for(Question x: questionList){
            if(x.getUser_answer()!=-1){
                answerd++;
            }
        }

        return answerd;
    }

    private int getCorrectAnswer(List<Question> questionList){
        int correctAnswer = 0;

        for(Question x: questionList){
            if(x.getUser_answer()==x.getAnswer()){
                correctAnswer++;
            }
        }

        return correctAnswer;
    }
}
