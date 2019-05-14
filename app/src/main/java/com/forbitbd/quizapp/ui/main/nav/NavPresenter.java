package com.forbitbd.quizapp.ui.main.nav;

public class NavPresenter implements NavContract.Presenter {

    private NavContract.View mView;

    public NavPresenter(NavContract.View mView) {
        this.mView = mView;
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
    public void loadContactUsFragment() {
        mView.loadContactUsFragment();
    }

    @Override
    public void logout() {
        mView.logout();
    }
}
