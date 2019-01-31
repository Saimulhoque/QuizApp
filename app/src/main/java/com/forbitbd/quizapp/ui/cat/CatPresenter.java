package com.forbitbd.quizapp.ui.cat;

import com.forbitbd.quizapp.api.CivilClient;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.QuestionsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatPresenter implements CatContract.Presenter {
    private CatContract.View mView;

    public CatPresenter(CatContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getAllQuestions(String catName) {
        CivilClient civilClient = ServiceGenerator.createService(CivilClient.class);

        Call<QuestionsResponse> call = civilClient.getCategorizeQuestions(catName);

        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if(response.code()==200){
                    QuestionsResponse questionsResponse = response.body();

                    mView.initialize(questionsResponse.getQuestions());
                }
            }

            @Override
            public void onFailure(Call<QuestionsResponse> call, Throwable t) {

            }
        });
    }
}
