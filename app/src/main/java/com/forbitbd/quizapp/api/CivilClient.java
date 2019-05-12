package com.forbitbd.quizapp.api;

import com.forbitbd.quizapp.model.CategoriesResponse;
import com.forbitbd.quizapp.model.LoginResponse;
import com.forbitbd.quizapp.model.MyToken;
import com.forbitbd.quizapp.model.PostResult;
import com.forbitbd.quizapp.model.QuestionsResponse;
import com.forbitbd.quizapp.model.ResultResponse;
import com.forbitbd.quizapp.model.User;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
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

    @GET("/users/{user_id}")
    Call<User> getUser(@Path("user_id") String user_id);

    @PUT("/users/{user_id}")
    @Multipart
    Call<User> updateProfile(@Path("user_id") String user_id,
                             @Part MultipartBody.Part file,
                             @PartMap() Map<String, RequestBody> partMap);

    @PUT("/users/updatetoken/{user_id}")
    Call<Void> updateToken(@Path("user_id") String user_id,@Body MyToken token);


    @POST("/results")
    Call<Void> postResult(@Body PostResult postResult);

    @GET("/results")
    Call<ResultResponse> getUserResults(@Query("email") String email);



}
