package com.forbitbd.quizapp.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.forbitbd.quizapp.PrebaseActivity;
import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.PostResult;
import com.forbitbd.quizapp.model.ResultResponse;
import com.forbitbd.quizapp.model.User;
import com.forbitbd.quizapp.ui.login.Login;
import com.forbitbd.quizapp.ui.main.home.HomeFragment;
import com.forbitbd.quizapp.ui.main.profile.ProfileFragment;
import com.forbitbd.quizapp.util.AdUtil;
import com.forbitbd.quizapp.util.AppPreference;
import com.forbitbd.quizapp.util.MyUtil;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends PrebaseActivity implements MainContract.View {

    private MainPresenter mPresenter;

    private TextView tvTitle;





    private List<PostResult> postResultList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this,AppPreference.getInstance(getApplicationContext()));


        if(AppPreference.getInstance(getApplicationContext()).isLogin()){
            initView();
        }else{
            mPresenter.startLoginActivity();
        }

        new AdUtil(this);


    }

    private void initView() {
       tvTitle = findViewById(R.id.title);
       setUpNavigationDrawer();
       mPresenter.getUser();
       //mPresenter.getAllCategories();
    }


    @Override
    public void renderNav(User user,boolean requestForResults) {

        getDrawer().renderNav(user);

        if(requestForResults){
            // Get Results of Questions
            mPresenter.getUserResults();
        }



    }


    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }

    @Override
    public void init(ResultResponse resultResponse) {
        this.postResultList = resultResponse.getResults();
        loadHomeFragment();
    }



    @Override
    public void loadHomeFragment() {
        if(!(getSupportFragmentManager().findFragmentById(R.id.main_container) instanceof HomeFragment)){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();
        }

    }

    @Override
    public void loadProfileFragment() {
        if(!(getSupportFragmentManager().findFragmentById(R.id.main_container) instanceof ProfileFragment)){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new ProfileFragment()).commit();
        }

    }

    @Override
    public void loadAboutUsFragment() {

    }

    @Override
    public void logout() {
        finish();
        AppPreference.getInstance(getApplicationContext()).setLogin(false);
        mPresenter.startLoginActivity();
    }


    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public List<PostResult> getPostResultList(){
        return this.postResultList;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Log.d("JJJJJJ",requestCode+" "+resultCode);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                    Bitmap scaledBitMap = MyUtil.getScaledBitmap(bitmap,200,200);

                    if(getSupportFragmentManager().findFragmentById(R.id.main_container) instanceof ProfileFragment){
                        ProfileFragment infoFragment = (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);

                        File f = new File(getCacheDir(), AppPreference.getInstance(this).getID());
                        f.createNewFile();

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        scaledBitMap.compress(Bitmap.CompressFormat.JPEG, 80 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

                        Log.d("UUUUUU",bitmapdata.length+"");
                        FileOutputStream fos = new FileOutputStream(f);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();

                        infoFragment.setBitmap(scaledBitMap,bitmapdata);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
