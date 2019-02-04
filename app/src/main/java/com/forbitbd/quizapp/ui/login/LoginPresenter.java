package com.forbitbd.quizapp.ui.login;


import android.util.Log;

import com.forbitbd.quizapp.api.CivilClient;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.LoginResponse;
import com.forbitbd.quizapp.model.User;
import com.forbitbd.quizapp.util.AppPreference;
import com.forbitbd.quizapp.util.MyUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private AppPreference appPreference;

    public LoginPresenter(LoginContract.View mView, AppPreference appPreference) {
        this.mView = mView;
        this.appPreference= appPreference;
    }

    @Override
    public void signupClick() {
        mView.startSignUpActivity();
    }

    @Override
    public void setSaveEmailAndPassword() {
        if(appPreference.getEmail()!=null){
            mView.renderEmail(appPreference.getEmail());
        }

        if(appPreference.getPassword()!=null){
            mView.renderPassword(appPreference.getPassword());
        }
    }

    @Override
    public boolean validate(User user) {
        mView.clearPreError();

        if(user.getEmail().equals("")){
            mView.showToastMessage("Empty Value Not Allowed",1);
            return false;
        }

        if(!MyUtil.isValidEmail(user.getEmail())){
            mView.showToastMessage("Email is not Valid",1);
            return false;
        }

        if(user.getPassword().equals("")){
            mView.showToastMessage("Empty Value Not Allowed",2);
            return false;
        }

        return true;
    }

    @Override
    public void login(final User user) {
        CivilClient client = ServiceGenerator.createService(CivilClient.class);
        Call<LoginResponse> call = client.login(user);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code()==201){
                    appPreference.setEmail(user.getEmail());
                    appPreference.setPassword(user.getPassword());
                    appPreference.setToken(response.body().getToken());
                    appPreference.setID(response.body().getUser().get_id());
                    appPreference.setLogin(true);
                    mView.startMainActivity();
                }else if(response.code()==401){
                    mView.showToast("Auth Failed !!!!",3);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mView.showToast("Auth failed",3);
            }
        });
    }
}
