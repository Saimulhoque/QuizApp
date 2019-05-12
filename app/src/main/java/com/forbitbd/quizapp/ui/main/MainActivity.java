package com.forbitbd.quizapp.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forbitbd.quizapp.PrebaseActivity;
import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.model.PostResult;
import com.forbitbd.quizapp.model.ResultResponse;
import com.forbitbd.quizapp.model.User;
import com.forbitbd.quizapp.ui.cat.CatQuestionActivity;
import com.forbitbd.quizapp.ui.login.Login;
import com.forbitbd.quizapp.ui.main.home.HomeFragment;
import com.forbitbd.quizapp.ui.main.profile.ProfileFragment;
import com.forbitbd.quizapp.util.AppPreference;
import com.forbitbd.quizapp.util.Constant;
import com.forbitbd.quizapp.util.MyUtil;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends PrebaseActivity implements MainContract.View,View.OnClickListener {

    private MainPresenter mPresenter;

    private TextView tvTitle;
    private ImageView ivHam,ivProfile;

    private FlowingDrawer mDrawer;


    private LinearLayout rvHome,rvProfile, rvContactUs, rvLogOut;

    private List<PostResult> postResultList;

    private TextView tvName,tvEmail;

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




    }

    private void initView() {

        tvName = findViewById(R.id.name);
        tvEmail = findViewById(R.id.email);
        ivProfile = findViewById(R.id.iv_profile);


        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);


       tvTitle = findViewById(R.id.title);

       ivHam = findViewById(R.id.ham);
       ivHam.setOnClickListener(this);

        rvHome = findViewById(R.id.home);
        rvProfile = findViewById(R.id.profile);
        rvContactUs = findViewById(R.id.contact_us);
        rvLogOut = findViewById(R.id.logout);


        rvHome.setOnClickListener(this);
        rvContactUs.setOnClickListener(this);
        rvLogOut.setOnClickListener(this);
        rvProfile.setOnClickListener(this);

        //mPresenter.loadHomeFragment();


        mPresenter.getUser();


       //mPresenter.getAllCategories();
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
    public void renderNav(User user) {

        if(user.getName()!=null){
            tvName.setText(user.getName());
        }

        if(user.getEmail()!=null){
            tvEmail.setText(user.getEmail());
        }

        if(user.getImage()!=null && !user.getImage().equals("")){

            Log.d("HHHHH",ServiceGenerator.BASE_URL+"/"+user.getImage());

            Log.d("UUUUU",user.getImage());
            Picasso.with(getApplicationContext())
                    .load(ServiceGenerator.BASE_URL+"/"+user.getImage())
                    .into(ivProfile);

        }

        // Get Results of Questions
        mPresenter.getUserResults();

    }

    @Override
    public void openDrawer() {
        if(mDrawer!=null){
            mDrawer.openMenu(true);
        }
    }

    @Override
    public void loadHomeFragment() {
        mDrawer.closeMenu(true);
        if(!(getSupportFragmentManager().findFragmentById(R.id.main_container) instanceof HomeFragment)){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();
        }

    }

    @Override
    public void loadProfileFragment() {
        mDrawer.closeMenu(true);
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
        mPresenter.startLoginActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ham:
                mPresenter.openDrawer();
                break;

            case R.id.home:
                mPresenter.loadHomeFragment();
                break;

            case R.id.profile:
                mPresenter.loadProfileFragment();
                break;

            case R.id.contact_us:
                break;

            case R.id.logout:
                mPresenter.logout();
                break;
        }
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
