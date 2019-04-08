package com.forbitbd.quizapp.ui.cat;

import android.util.Log;

import com.forbitbd.quizapp.api.CivilClient;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.PostResult;
import com.forbitbd.quizapp.model.QuestionsResponse;
import com.forbitbd.quizapp.model.Result;
import com.forbitbd.quizapp.util.AppPreference;

import java.util.List;

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

    @Override
    public void postResultToDatabase(String catName, String subcat, List<Result> results) {
        mView.showDialog();
        CivilClient civilClient = ServiceGenerator.createService(CivilClient.class);
        PostResult postResult = new PostResult();
        postResult.setCat_name(catName);
        postResult.setSubcat_name(subcat);
        postResult.setEmail(appPreference.getEmail());
        postResult.setResults(results);

        civilClient.postResult(postResult)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==201){
                            Log.d("HHHHH","OK");

                            mView.startResultActivity();


                        }else{
                            Log.d("HHHHH","Status Error");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("HHHHH","Error "+t.getMessage());
                    }
                });
    }
}
