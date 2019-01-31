package com.forbitbd.quizapp.api;

import com.forbitbd.quizapp.model.CategoriesResponse;
import com.forbitbd.quizapp.model.QuestionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CivilClient {

    @GET("/questions")
    Call<QuestionsResponse> getCategorizeQuestions(@Query("category") String category);

    @GET("/categories")
    Call<CategoriesResponse> getAllCategories();


}
