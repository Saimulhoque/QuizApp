package com.forbitbd.quizapp.ui.main.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.ui.cat.CatQuestionActivity;
import com.forbitbd.quizapp.ui.main.BaseFragment;
import com.forbitbd.quizapp.util.Constant;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    private RecyclerView mRecyclerView;

    private CatAdapter adapter;

    private HomePresenter mPresenter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new HomePresenter(this);
        adapter = new CatAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);

        mPresenter.getAllCategories();
    }


    @Override
    public void onResume() {
        super.onResume();

        setTitle("Categories");
    }

    @Override
    public void renderRecyclerView(List<Category> categoryList) {
        for (Category x: categoryList){
            adapter.addCategory(x);

            Log.d("UUUUU",x.getSubcats().get(0));
        }
    }

    @Override
    public void itemClick(Category category,int position) {
        Intent intent = new Intent(getContext(), CatQuestionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.CATEGORY,category);
        bundle.putInt(Constant.SUBCAT_POS,position);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
