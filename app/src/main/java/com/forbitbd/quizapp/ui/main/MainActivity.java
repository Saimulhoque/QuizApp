package com.forbitbd.quizapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.forbitbd.quizapp.PrebaseActivity;
import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.ui.cat.CatQuestionActivity;
import com.forbitbd.quizapp.util.Constant;

import java.util.List;

public class MainActivity extends PrebaseActivity implements MainContract.View {

    private MainPresenter mPresenter;

    private RecyclerView mRecyclerView;

    private CatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Categories");

        mPresenter = new MainPresenter(this);

        adapter = new CatAdapter(this);

        initView();


    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(adapter);

        mPresenter.getAllCategories();
    }

    @Override
    public void renderRecyclerView(List<Category> categoryList) {
        for (Category x: categoryList){
            adapter.addCategory(x);
        }
    }

    @Override
    public void itemClick(Category category) {
        Intent intent = new Intent(getApplicationContext(), CatQuestionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.CATEGORY,category);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
