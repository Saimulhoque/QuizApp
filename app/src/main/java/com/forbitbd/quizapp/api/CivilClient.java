package com.forbitbd.quizapp.api;

import com.forbitbd.quizapp.model.CategoriesResponse;
import com.forbitbd.quizapp.model.LoginResponse;
import com.forbitbd.quizapp.model.PostResult;
import com.forbitbd.quizapp.model.QuestionsResponse;
import com.forbitbd.quizapp.model.ResultResponse;
import com.forbitbd.quizapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CivilClient {

    @GET("/questions")
    Call<QuestionsResponse> getCategorizeQuestions(@Header("Authorization") String token, @Query("category") String category, @Query("subcat") String subcat);

    @GET("/categories")
    Call<CategoriesResponse> getAllCategories();

    @POST("/users/signup")
    Call<Void> signUp(@Body User user);

    @POST("/users/login")
    Call<LoginResponse> login(@Body User user);

    @POST("/results")
    Call<Void> postResult(@Body PostResult postResult);

    @GET("/results")
    Call<ResultResponse> getUserResults(@Query("email") String email);



}
