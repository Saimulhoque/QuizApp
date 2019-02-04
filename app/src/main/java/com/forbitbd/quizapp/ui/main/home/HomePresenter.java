package com.forbitbd.quizapp.ui.main.home;

import com.forbitbd.quizapp.api.CivilClient;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.CategoriesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getAllCategories() {
        CivilClient civilClient = ServiceGenerator.createService(CivilClient.class);

        Call<CategoriesResponse> call = civilClient.getAllCategories();

        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if(response.code()==200){
                    CategoriesResponse categoriesResponse = response.body();

                    mView.renderRecyclerView(categoriesResponse.getCategories());
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
        });
    }
}
