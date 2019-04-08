package com.forbitbd.quizapp.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.forbitbd.quizapp.model.PostResult;

public class BaseFragment extends Fragment {

    private MainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getActivity() instanceof MainActivity){
            activity = (MainActivity) getActivity();
        }
    }


    public void setTitle(String title){
        if(activity!=null){
            activity.setTitle(title);
        }
    }

    public PostResult getResult(String subcategory){
        for (PostResult postResult:activity.getPostResultList()){
            if(postResult.getSubcat_name().equals(subcategory)){
                return postResult;
            }
        }
        return null;
    }



}
