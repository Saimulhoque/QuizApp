package com.forbitbd.quizapp.ui.main.profile;

import android.content.Context;
import android.util.Log;


import com.forbitbd.quizapp.api.CivilClient;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.User;
import com.forbitbd.quizapp.util.AppPreference;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View mView;
    private AppPreference appPreference;
    private Context context;

    public ProfilePresenter(ProfileContract.View mView, Context context) {
        this.context = context;
        this.mView = mView;
        this.appPreference = AppPreference.getInstance(context);
    }

    @Override
    public void requestForUser() {
        Log.d("HHHH","JJJJJ");
        Log.d("HHHH",""+appPreference.getID());
        CivilClient cmClient = ServiceGenerator.createService(CivilClient.class);
        cmClient.getUser(appPreference.getID())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.code()==200){
                            User user = response.body();
                            mView.renderUI(user);

                            Log.d("HHHH","JJJJJ");
                            Log.d("HHHH",response.code()+"");

                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
    }

    @Override
    public boolean validate(User user) {
        mView.clearPreErrors();

        if(user.getName()==null || user.getName().equals("")){
            mView.showErrorMessage(1,"Please Enter Your Name");
            return false;
        }

        if(user.getPhone()==null || user.getPhone().equals("")){
            mView.showErrorMessage(2,"Contact Number Empty");
            return false;
        }

        if(user.getAddress()==null || user.getAddress().equals("")){
            mView.showErrorMessage(3,"Address Empty");
            return false;
        }


        return true;
    }

    @Override
    public void updateUser(User user, byte[] bytes) {


        MultipartBody.Part part=null;

       if(bytes!=null){
           RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), bytes);
           //MultipartBody.Part body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
          // RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), bytes);
           // Create MultipartBody.Part using file request-body,file name and part name
           part = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
       }


        // Create a request body with file and image media type



        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), user.getName());
        RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), user.getPhone());
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), user.getAddress());

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("name", name);
        map.put("phone", phone);
        map.put("address", address);

        CivilClient cmClient = ServiceGenerator.createService(CivilClient.class);
        cmClient.updateProfile(appPreference.getID(),part,map).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("HHHH","JJJJJJ");
                if(response.code()==201){
                    User updatedUser = response.body();
                    mView.updateUser(updatedUser);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("HHHH","JJJJJJ "+t.getMessage());
            }
        });


    }

    @Override
    public void uploadUserImage(byte[] bytes) {

    }

    @Override
    public void browseClick() {
        mView.openCropImageActivity();
    }
}
