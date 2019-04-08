package com.forbitbd.quizapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forbitbd.quizapp.PrebaseActivity;
import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.model.PostResult;
import com.forbitbd.quizapp.model.ResultResponse;
import com.forbitbd.quizapp.ui.cat.CatQuestionActivity;
import com.forbitbd.quizapp.ui.login.Login;
import com.forbitbd.quizapp.ui.main.home.HomeFragment;
import com.forbitbd.quizapp.util.AppPreference;
import com.forbitbd.quizapp.util.Constant;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View,View.OnClickListener {

    private MainPresenter mPresenter;

    private TextView tvTitle;
    private ImageView ivHam;

    private FlowingDrawer mDrawer;


    private LinearLayout rvHome,rvProfile, rvContactUs, rvLogOut;

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



        //adapter = new CatAdapter(this);




    }

    private void initView() {


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
        mPresenter.getUserResults();


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
}
