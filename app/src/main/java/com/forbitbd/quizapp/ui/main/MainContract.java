package com.forbitbd.quizapp.ui.main;

import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.model.ResultResponse;
import com.forbitbd.quizapp.model.User;

import java.util.List;

public interface MainContract {

    interface Presenter{

        void getUser();
        void startLoginActivity();
        void getUserResults();
    }

    interface View{
        void startLoginActivity();
        void init(ResultResponse resultResponse);

        void renderNav(User user,boolean requestForResults);

        void loadHomeFragment();
        void loadProfileFragment();
        void loadAboutUsFragment();
        void logout();
    }
}
