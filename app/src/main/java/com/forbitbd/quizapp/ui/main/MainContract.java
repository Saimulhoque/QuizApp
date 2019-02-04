package com.forbitbd.quizapp.ui.main;

import com.forbitbd.quizapp.model.Category;

import java.util.List;

public interface MainContract {

    interface Presenter{

        void startLoginActivity();
        void openDrawer();
        void loadHomeFragment();
        void loadProfileFragment();
        void loadAboutUsFragment();
        void logout();
    }

    interface View{
        void startLoginActivity();

        void openDrawer();
        void loadHomeFragment();
        void loadProfileFragment();
        void loadAboutUsFragment();
        void logout();
    }
}
