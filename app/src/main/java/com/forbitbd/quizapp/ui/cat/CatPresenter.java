package com.forbitbd.quizapp.ui.cat;

import android.util.Log;

import com.forbitbd.quizapp.api.CivilClient;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.QuestionsResponse;
import com.forbitbd.quizapp.util.AppPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatPresenter implements CatContract.Presenter {
    private CatContract.View mView;
    private AppPreference appPreference;

    public CatPresenter(CatContract.View mView, AppPreference appPreference) {
        this.mView = mView;
        this.appPreference = appPreference;
    }

    @Override
    public void getAllQuestions(String catName,String subcat) {
        CivilClient civilClient = ServiceGenerator.createService(CivilClient.class);
        String token = "Bearer "+appPreference.getToken();

        Log.d("HHHHH","Sub Cat "+subcat);

        Call<QuestionsResponse> call = civilClient.getCategorizeQuestions(token,catName,subcat);

        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if(response.code()==200) {
                    QuestionsResponse questionsResponse = response.body();
                    mView.initialize(questionsResponse.getQuestions());
                }else if(response.code()==401){
                    appPreference.setLogin(false);
                    mView.startLoginActivity();
                }
            }

            @Override
            public void onFailure(Call<QuestionsResponse> call, Throwable t) {

            }
        });
    }
}
