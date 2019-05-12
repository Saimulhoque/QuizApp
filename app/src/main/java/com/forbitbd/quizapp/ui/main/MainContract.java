package com.forbitbd.quizapp.ui.main;

import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.model.ResultResponse;
import com.forbitbd.quizapp.model.User;

import java.util.List;

public interface MainContract {

    interface Presenter{

        void getUser();

        void startLoginActivity();
        void openDrawer();
        void loadHomeFragment();
        void loadProfileFragment();
        void loadAboutUsFragment();
        void getUserResults();
        void logout();
    }

    interface View{
        void startLoginActivity();
        void init(ResultResponse resultResponse);

        void renderNav(User user);

        void openDrawer();
        void loadHomeFragment();
        void loadProfileFragment();
        void loadAboutUsFragment();
        void logout();
    }
}
