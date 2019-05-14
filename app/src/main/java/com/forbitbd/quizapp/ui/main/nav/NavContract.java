package com.forbitbd.quizapp.ui.main.nav;

import com.forbitbd.quizapp.model.User;

public interface NavContract {

    interface Presenter{
        void loadHomeFragment();
        void loadProfileFragment();
        void loadContactUsFragment();
        void logout();
    }

    interface View{
        void loadHomeFragment();
        void loadProfileFragment();
        void loadContactUsFragment();

        void renderNav(User user);
        void logout();
    }
}
