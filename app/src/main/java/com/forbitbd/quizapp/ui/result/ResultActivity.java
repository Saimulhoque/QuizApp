package com.forbitbd.quizapp.ui.result;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.forbitbd.quizapp.PrebaseActivity;
import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.model.Question;
import com.forbitbd.quizapp.util.Constant;

import java.util.List;

public class ResultActivity extends PrebaseActivity implements ResultContract.View {

    private List<Question> questionList;
    private Category category;
    private String subCat;

    private TextView tvTotal,tvAnswered,tvLeave,tvCorrect;

    private ResultPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mPresenter = new ResultPresenter(this);

        this.questionList = (List<Question>) getIntent().getSerializableExtra(Constant.QUESTION_LIST);
        this.subCat = getIntent().getStringExtra(Constant.SUBCAT);
        this.category = (Category) getIntent().getSerializableExtra(Constant.CATEGORY);

        adapter = new QuestionAdapter(getApplicationContext(),questionList);


        setupToolbar();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(category.getDisplay_name()+" | "+subCat);

        initView();




        Log.d("HHHHHH",questionList.size()+"");
    }

    private void initView() {
        tvTotal = findViewById(R.id.total_questions);
        tvAnswered = findViewById(R.id.answered);
        tvLeave = findViewById(R.id.leave);
        tvCorrect = findViewById(R.id.correct_answer);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(adapter);

        mPresenter.renderResult(questionList);
    }

    @Override
    public void renderTotalTextView(String value) {
        tvTotal.setText(value);
    }

    @Override
    public void renderAnsweredTextView(String value) {
        tvAnswered.setText(value);
    }

    @Override
    public void renderLivedTextView(String value) {
        tvLeave.setText(value);
    }

    @Override
    public void renderCorrectAnswerTextView(String value) {
        tvCorrect.setText(value);
    }
}
