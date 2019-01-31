package com.forbitbd.quizapp.ui.cat.question;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Question;
import com.forbitbd.quizapp.ui.cat.CatQuestionActivity;
import com.forbitbd.quizapp.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener,QuestionContract.View{

    private TextView tvQuestion,tvNext,tvPrevious;
    private RadioGroup mRadioGroup;
    private RadioButton rOne,rTwo,rThree,rFour,rFive;

    private Question question;

    private CatQuestionActivity activity;

    private int radioId;

    private QuestionPresenter mPresenter;



    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new QuestionPresenter(this);
        question = (Question) getArguments().getSerializable(Constant.QUESTION);
        if(getActivity() instanceof CatQuestionActivity){
            activity = (CatQuestionActivity) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        tvQuestion = view.findViewById(R.id.question);
        tvNext = view.findViewById(R.id.next);
        tvPrevious = view.findViewById(R.id.previous);
        mRadioGroup = view.findViewById(R.id.radio_group);
        rOne = view.findViewById(R.id.opt_one);
        rTwo = view.findViewById(R.id.opt_two);
        rThree = view.findViewById(R.id.opt_three);
        rFour = view.findViewById(R.id.opt_four);
        rFive = view.findViewById(R.id.opt_five);

        tvQuestion.setText(question.getQuestion());

        if(question.getOptions().length==2){
            rOne.setText(question.getOptions()[0]);
            rTwo.setText(question.getOptions()[1]);
            rThree.setVisibility(View.GONE);
            rFour.setVisibility(View.GONE);
            rFive.setVisibility(View.GONE);

        }else if(question.getOptions().length==3){
            rOne.setText(question.getOptions()[0]);
            rTwo.setText(question.getOptions()[1]);
            rThree.setText(question.getOptions()[2]);
            rFour.setVisibility(View.GONE);
            rFive.setVisibility(View.GONE);
        }else if(question.getOptions().length==4){
            rOne.setText(question.getOptions()[0]);
            rTwo.setText(question.getOptions()[1]);
            rThree.setText(question.getOptions()[2]);
            rFour.setText(question.getOptions()[3]);
            rFive.setVisibility(View.GONE);
        }else if(question.getOptions().length==5){
            rOne.setText(question.getOptions()[0]);
            rTwo.setText(question.getOptions()[1]);
            rThree.setText(question.getOptions()[2]);
            rFour.setText(question.getOptions()[3]);
            rFive.setText(question.getOptions()[4]);
        }


        tvPrevious.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mPresenter.performAction(i);
            }
        });


    }

    public void controlPrevNext(int currentPosition,int total){
        if(currentPosition!=0){
            tvPrevious.setVisibility(View.VISIBLE);
        }

        if(currentPosition==total-1){
            tvNext.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.previous:
                if(activity!=null){
                    activity.previous();
                }
                break;

            case R.id.next:
                activity.next();
                break;


        }
    }

    @Override
    public void performAction(int buttonId) {
        switch (buttonId){
             case R.id.opt_one:
                 if(rOne.isChecked()){
                     question.setUser_answer(0);
                 }else {
                     question.setUser_answer(-1);
                 }
               break;

            case R.id.opt_two:
                if(rTwo.isChecked()){
                    question.setUser_answer(1);
                }else {
                    question.setUser_answer(-1);
                }
                break;

            case R.id.opt_three:
                if(rThree.isChecked()){
                    question.setUser_answer(2);
                }else {
                    question.setUser_answer(-1);
                }
                break;

            case R.id.opt_four:
                if(rFour.isChecked()){
                    question.setUser_answer(3);
                }else {
                    question.setUser_answer(-1);
                }
                break;

            case R.id.opt_five:
                if(rFive.isChecked()){
                    question.setUser_answer(4);
                }else {
                    question.setUser_answer(-1);
                }
                break;
        }


        // Update Question in Activity
        activity.updateQuestion(question);

        Log.d("HHHHHHHHH",question.getUser_answer()+"");


    }
}
