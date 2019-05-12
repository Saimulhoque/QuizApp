package com.forbitbd.quizapp.ui.main.home;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.model.Category;
import com.forbitbd.quizapp.model.PostResult;
import com.forbitbd.quizapp.model.SubCategory;

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
        SubCategory subCat = category.getSubcats().get(position);
        holder.bind(subCat);


    }

    @Override
    public int getItemCount() {
        return category.getSubcats().size();
    }

    class SubcatHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvName,tvResult;

        public SubcatHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvResult = itemView.findViewById(R.id.result);
            itemView.setOnClickListener(this);
        }

        public void bind(SubCategory subCategory){
            tvName.setText(subCategory.getDisplay_name());

            if(mFragment instanceof HomeFragment){
                HomeFragment homeFragment = (HomeFragment) mFragment;
                List<PostResult> postResults = homeFragment.getResults(subCategory.getName());

                if(postResults==null){
                    tvResult.setVisibility(View.GONE);
                    //tvResult.setText("0 Times");
                }else{
                    Log.d("FFFFFFFFUUUUUUUU","NOT NULL");
                    tvResult.setVisibility(View.VISIBLE);
                    tvResult.setText(postResults.size()+" Times");

                }
            }
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
