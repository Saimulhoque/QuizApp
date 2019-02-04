package com.forbitbd.quizapp.ui.main;

import com.forbitbd.quizapp.api.CivilClient;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.CategoriesResponse;
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
    public void logout() {
        appPreference.setLogin(false);
        mView.logout();
    }
}
