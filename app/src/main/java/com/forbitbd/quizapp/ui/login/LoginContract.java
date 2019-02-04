package com.forbitbd.quizapp.ui.login;

import com.forbitbd.quizapp.model.User;

public interface LoginContract {

    interface Presenter{
        void signupClick();
        void setSaveEmailAndPassword();

        boolean validate(User user);

        void login(User user);
    }

    interface View{
        void startSignUpActivity();
        void startMainActivity();
        void renderEmail(String email);
        void renderPassword(String password);

        void clearPreError();
        void showToastMessage(String message, int fieldId);

        void showToast(String message, int fieldId);

    }
}
