package com.forbitbd.quizapp.ui.signup;

import android.util.Log;

import com.forbitbd.quizapp.api.CivilClient;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.User;
import com.forbitbd.quizapp.util.MyUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mView;

    public SignUpPresenter(SignUpContract.View mView) {
        this.mView = mView;
    }

    @Override
    public boolean validate(String email, String password) {
        mView.clearPreError();

        if(email.equals("")){
            mView.showToastMessage("Empty Value Not Allowed",1);
            return false;
        }

        if(!MyUtil.isValidEmail(email)){
            mView.showToastMessage("Email is not Valid",1);
            return false;
        }

        if(password.equals("")){
            mView.showToastMessage("Empty Value Not Allowed",2);
            return false;
        }

        return true;
    }

    @Override
    public void signUp(String email, String password) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        CivilClient civilClient = ServiceGenerator.createService(CivilClient.class);

        Call<Void> call = civilClient.signUp(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==201){
                   mView.startLoginActivity();
                    mView.showToast("Successfully Register your Account. Now You can Login",1);
                }else if(response.code()==409){
                    Log.d("HHHHHH",response.code()+" YESS");
                    mView.showToast("Email Already Taken",2);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("HHHHHH","Error Happened");
                mView.showToast("Error in Complete Operation",3);
            }
        });
    }
}
