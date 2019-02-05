package com.forbitbd.quizapp.ui.cat;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.forbitbd.quizapp.PrebaseActivity;
import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.model.Question;
import com.forbitbd.quizapp.ui.cat.question.QuestionFragment;
import com.forbitbd.quizapp.ui.login.Login;
import com.forbitbd.quizapp.ui.result.ResultActivity;
import com.forbitbd.quizapp.util.AppPreference;
import com.forbitbd.quizapp.util.Constant;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CatQuestionActivity extends PrebaseActivity implements CatContract.View ,View.OnClickListener{

    private CatPresenter mPresenter;

    private Category category;
    private String subcat;

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private List<Question> questionList;

    private TextView tvCurrent,tvTotal;
    private RelativeLayout rIndicator;

    private int currentItem;

    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_question);

        this.questionList = new ArrayList<>();

        mPresenter = new CatPresenter(this, AppPreference.getInstance(getApplicationContext()));

        category = (Category) getIntent().getSerializableExtra(Constant.CATEGORY);
        int position = getIntent().getIntExtra(Constant.SUBCAT_POS,0);
        subcat = category.getSubcats().get(position);

        setupToolbar();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(category.getName());

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Log.d("HHHHHH",category.getName());

        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        //setupViewPager(viewPager);
        mPresenter.getAllQuestions(category.getName(),subcat);

        rIndicator = findViewById(R.id.indicator);
        tvCurrent = findViewById(R.id.current);
        tvTotal = findViewById(R.id.total);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                tvCurrent.setText(String.valueOf(position+1));

                if(adapter.getItem(position) instanceof QuestionFragment){
                    QuestionFragment qf = (QuestionFragment) adapter.getItem(position);
                    qf.controlPrevNext(position,questionList.size());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnSubmit = findViewById(R.id.submit);
        btnSubmit.setOnClickListener(this);
    }

    public void previous(){
        viewPager.setCurrentItem(currentItem-1,true);
    }

    public void next(){
        viewPager.setCurrentItem(currentItem+1,true);
    }


    public void updateQuestion(Question question){
        this.questionList.set(currentItem,question);

        Log.d("UUUUUU",questionList.get(currentItem).getUser_answer()+"");
    }

    @Override
    public void initialize(List<Question> questionList) {
        this.questionList = questionList;

        tvTotal.setText(String.valueOf(questionList.size()));
        rIndicator.setVisibility(View.VISIBLE);

        for (int i=0;i<questionList.size();i++){
            QuestionFragment questionFragment = new QuestionFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.QUESTION,questionList.get(i));
            questionFragment.setArguments(bundle);

            adapter.addFragment(questionFragment);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void startLoginActivity() {
        TastyToast.makeText(getApplicationContext(),"Authorization Failed",TastyToast.LENGTH_LONG,TastyToast.ERROR);
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.QUESTION_LIST, (Serializable) questionList);
        bundle.putString(Constant.SUBCAT,subcat);
        bundle.putSerializable(Constant.CATEGORY,category);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
