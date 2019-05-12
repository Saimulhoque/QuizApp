package com.forbitbd.quizapp.ui.main;

import android.util.Log;

import com.forbitbd.quizapp.api.CivilClient;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.CategoriesResponse;
import com.forbitbd.quizapp.model.ResultResponse;
import com.forbitbd.quizapp.model.User;
import com.forbitbd.quizapp.util.AppPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private AppPreference appPreference;

    public MainPresenter(MainContract.View mView,AppPreference appPreference) {
        this.mView = mView;
        this.appPreference = appPreference;
    }


    @Override
    public void getUser() {

        CivilClient cmClient = ServiceGenerator.createService(CivilClient.class);
        Call<User> call = cmClient.getUser(appPreference.getID());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()==200){
                    Log.d("JJJJJ","Found");
                    User user = response.body();

                    if(user==null){
                        mView.logout();
                    }else{
                        mView.renderNav(user);

                    }



                    //Todo Uncomment next

                }else{
                    Log.d("JJJJJ","Not Found "+response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("JJJJJ","Error "+t.getMessage());
            }
        });

    }

    @Override
    public void startLoginActivity() {
        mView.startLoginActivity();
    }

    @Override
    public void openDrawer() {
        mView.openDrawer();
    }

    @Override
    public void loadHomeFragment() {
        mView.loadHomeFragment();
    }

    @Override
    public void loadProfileFragment() {
        mView.loadProfileFragment();
    }

    @Override
    public void loadAboutUsFragment() {
        mView.loadAboutUsFragment();
    }

    @Override
    public void getUserResults() {
        if(appPreference.getEmail()!=null){
            CivilClient civilClient = ServiceGenerator.createService(CivilClient.class);

            Call<ResultResponse> call = civilClient.getUserResults(appPreference.getEmail());

            call.enqueue(new Callback<ResultResponse>() {
                @Override
                public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                    if(response.code()==200){
                        Log.d("UUUUU","OK");
                        mView.init(response.body());
                    }else {
                        Log.d("UUUUU","NOT OK");
                    }
                }

                @Override
                public void onFailure(Call<ResultResponse> call, Throwable t) {
                    Log.d("UUUUU","NOT OK "+t.getMessage());
                }
            });
        }
    }

    @Override
    public void logout() {
        appPreference.setLogin(false);
        mView.logout();
    }
}
