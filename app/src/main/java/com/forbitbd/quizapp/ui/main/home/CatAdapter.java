package com.forbitbd.quizapp.ui.main.home;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alespero.expandablecardview.ExpandableCardView;
import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatHolder> {

    private List<Category> categoryList;
    private LayoutInflater inflater;
    private Fragment mFragment;


    public CatAdapter(Fragment fragment) {
        this.mFragment = fragment;
        this.categoryList = new ArrayList<>();

        this.inflater = LayoutInflater.from(fragment.getContext());
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
        ExpandableCardView tvCategory;
        RecyclerView rvSubcat;

        public CatHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.category);
            rvSubcat = itemView.findViewById(R.id.rv_subcat);
            rvSubcat.setLayoutManager(new LinearLayoutManager(mFragment.getContext()));
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            /*if(mFragment instanceof HomeFragment){
                HomeFragment hf = (HomeFragment) mFragment;
                hf.itemClick(categoryList.get(getAdapterPosition()));
            }*/
        }

        public void bind(Category category){
            tvCategory.setTitle(category.getName());
            SubcatAdapter adapter = new SubcatAdapter(mFragment,category);
            rvSubcat.setAdapter(adapter);
        }
    }
}
