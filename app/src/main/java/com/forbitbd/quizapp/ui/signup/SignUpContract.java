package com.forbitbd.quizapp.ui.signup;

import java.lang.ref.SoftReference;

public interface SignUpContract {

    interface Presenter{
        boolean validate(String email,String password);

        void signUp(String email, String password);

    }

    interface View{

        void clearPreError();

        void showToastMessage(String message, int fieldId);

        void startLoginActivity();
        void showToast(String message,int type);

    }
}
