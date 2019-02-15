package com.forbitbd.quizapp.ui.main.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;

import java.util.List;

public class SubcatAdapter extends RecyclerView.Adapter<SubcatAdapter.SubcatHolder>{

    private Category category;
    private LayoutInflater inflater;
    private Fragment mFragment;


    public SubcatAdapter(Fragment fragment, Category category) {
        this.category = category;
        this.mFragment = fragment;
        this.inflater = LayoutInflater.from(fragment.getContext());
    }

    @NonNull
    @Override
    public SubcatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sub_cat_item,parent,false);
        return new SubcatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcatHolder holder, int position) {
        String subCat = category.getSubcats().get(position).getDisplay_name();
        holder.bind(subCat);
    }

    @Override
    public int getItemCount() {
        return category.getSubcats().size();
    }

    class SubcatHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvName;

        public SubcatHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);

            itemView.setOnClickListener(this);
        }

        public void bind(String name){
            tvName.setText(name);
        }

        @Override
        public void onClick(View view) {
            if(mFragment instanceof HomeFragment){
                HomeFragment homeFragment = (HomeFragment) mFragment;
                homeFragment.itemClick(category,category.getSubcats().get(getAdapterPosition()));
            }
        }
    }
}
