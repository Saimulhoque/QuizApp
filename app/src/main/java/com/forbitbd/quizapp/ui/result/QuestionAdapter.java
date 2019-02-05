package com.forbitbd.quizapp.ui.result;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    private Context context;
    private List<Question> questionList;
    private LayoutInflater inflater;

    public QuestionAdapter(Context context,List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_question,parent,false);

        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {

        Question question = questionList.get(position);
        holder.bind(question);

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class QuestionHolder extends RecyclerView.ViewHolder{

        TextView tvQuestion;
        TextView rOne,rTwo,rThree,rFour,rFive;

        ImageView ivQuestion,ivOne,ivTwo,ivThree,ivFour,ivFive;
        LinearLayout lOne,lTwo,lThree,lFour,lFive;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.question);
            ivQuestion = itemView.findViewById(R.id.iv_question);

            rOne = itemView.findViewById(R.id.opt_one);
            rTwo = itemView.findViewById(R.id.opt_two);
            rThree = itemView.findViewById(R.id.opt_three);
            rFour = itemView.findViewById(R.id.opt_four);
            rFive = itemView.findViewById(R.id.opt_five);

            lOne = itemView.findViewById(R.id.one);
            lTwo = itemView.findViewById(R.id.two);
            lThree = itemView.findViewById(R.id.three);
            lFour = itemView.findViewById(R.id.four);
            lFive = itemView.findViewById(R.id.five);

            ivOne = itemView.findViewById(R.id.iv_opt_one);
            ivTwo = itemView.findViewById(R.id.iv_opt_two);
            ivThree = itemView.findViewById(R.id.iv_opt_three);
            ivFour = itemView.findViewById(R.id.iv_opt_four);
            ivFive = itemView.findViewById(R.id.iv_opt_five);

        }


        public void bind(Question question){
            tvQuestion.setText(question.getQuestion());

            if(question.getAnswer()==question.getUser_answer()){
                ivQuestion.setImageResource(R.drawable.tick);
            }else{
                ivQuestion.setImageResource(R.drawable.wrong);
                //tvQuestion.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.wrong),null,null,null);
            }


            if(question.getOptions().length==2){
                rOne.setText(question.getOptions()[0]);
                rTwo.setText(question.getOptions()[1]);

                lOne.setVisibility(View.VISIBLE);
                lTwo.setVisibility(View.VISIBLE);
                lThree.setVisibility(View.GONE);
                lFour.setVisibility(View.GONE);
                lFive.setVisibility(View.GONE);

            }else if(question.getOptions().length==3){
                rOne.setText(question.getOptions()[0]);
                rTwo.setText(question.getOptions()[1]);
                rThree.setText(question.getOptions()[2]);

                lOne.setVisibility(View.VISIBLE);
                lTwo.setVisibility(View.VISIBLE);
                lThree.setVisibility(View.VISIBLE);
                lFour.setVisibility(View.GONE);
                lFive.setVisibility(View.GONE);
            }else if(question.getOptions().length==4){
                rOne.setText(question.getOptions()[0]);
                rTwo.setText(question.getOptions()[1]);
                rThree.setText(question.getOptions()[2]);
                rFour.setText(question.getOptions()[3]);

                lOne.setVisibility(View.VISIBLE);
                lTwo.setVisibility(View.VISIBLE);
                lThree.setVisibility(View.VISIBLE);
                lFour.setVisibility(View.VISIBLE);
                lFive.setVisibility(View.GONE);
            }else if(question.getOptions().length==5) {
                rOne.setText(question.getOptions()[0]);
                rTwo.setText(question.getOptions()[1]);
                rThree.setText(question.getOptions()[2]);
                rFour.setText(question.getOptions()[3]);
                rFive.setText(question.getOptions()[4]);


                lOne.setVisibility(View.VISIBLE);
                lTwo.setVisibility(View.VISIBLE);
                lThree.setVisibility(View.VISIBLE);
                lFour.setVisibility(View.VISIBLE);
                lFive.setVisibility(View.VISIBLE);
            }

            renderAnswer(question.getUser_answer(),question.getAnswer());
        }

        private ImageView[] getViewArr(){
            return new ImageView[]{ivOne,ivTwo,ivThree,ivFour,ivFive};
        }



        private void renderAnswer(int userAnswer,int answer){
            if(answer==userAnswer || userAnswer==-1){
                for (int i=0;i<getViewArr().length;i++){
                    if(i==answer){
                        getViewArr()[i].setImageResource(R.drawable.tick);
                    }else {
                        getViewArr()[i].setImageDrawable(null);
                    }
                }

            }else{

                for (int i=0;i<getViewArr().length;i++){
                    if(i==answer){
                        getViewArr()[i].setImageResource(R.drawable.tick);
                    }else if(i==userAnswer) {
                        getViewArr()[i].setImageResource(R.drawable.wrong);
                    }else{
                        getViewArr()[i].setImageDrawable(null);
                    }
                }

            }
        }
    }
}
