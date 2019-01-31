package com.forbitbd.quizapp.ui.main;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatHolder> {

    private List<Category> categoryList;
    private LayoutInflater inflater;
    private Activity activity;


    public CatAdapter(Activity activity) {
        this.activity = activity;
        this.categoryList = new ArrayList<>();

        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public CatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = inflater.inflate(R.layout.cat_item,parent,false);
       return new CatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void addCategory(Category category){
        categoryList.add(category);
        int position = categoryList.indexOf(category);
        notifyItemInserted(position);
    }

    class CatHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;

        public CatHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(activity instanceof MainActivity){
                MainActivity ma = (MainActivity) activity;
                ma.itemClick(categoryList.get(getAdapterPosition()));
            }
        }

        public void bind(Category category){
            tvName.setText(category.getName());
        }
    }
}
