package com.forbitbd.quizapp.ui.cat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.forbitbd.quizapp.PrebaseActivity;
import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.model.Question;
import com.forbitbd.quizapp.ui.cat.question.QuestionFragment;
import com.forbitbd.quizapp.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class CatQuestionActivity extends PrebaseActivity implements CatContract.View {

    private CatPresenter mPresenter;

    private Category category;

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private List<Question> questionList;

    private TextView tvCurrent,tvTotal;
    private RelativeLayout rIndicator;

    private int currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_question);

        this.questionList = new ArrayList<>();

        mPresenter = new CatPresenter(this);

        category = (Category) getIntent().getSerializableExtra(Constant.CATEGORY);

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
        mPresenter.getAllQuestions(category.getName());

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
