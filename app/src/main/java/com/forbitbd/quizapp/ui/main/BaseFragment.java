package com.forbitbd.quizapp.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.forbitbd.quizapp.model.PostResult;
import com.forbitbd.quizapp.model.User;

import java.util.ArrayList;
import java.util.List;

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

    public List<PostResult> getResults(String subcategory){
        List<PostResult> postResults = new ArrayList<>();
        for (PostResult postResult:activity.getPostResultList()){
            if(postResult.getSubcat_name().equals(subcategory)){
                postResults.add(postResult);
            }
        }
        return postResults;
    }

    public boolean isOnline(){
        return activity.isOnline();
    }

    public void updateUI(User user){
        activity.renderNav(user);
    }

    public void cropImage(){
        activity.openCropImageActivity(true);
    }




}
