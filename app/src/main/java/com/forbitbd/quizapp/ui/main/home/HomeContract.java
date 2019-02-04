package com.forbitbd.quizapp.ui.main.home;

import com.forbitbd.quizapp.model.Category;

import java.util.List;

public interface HomeContract {

    interface Presenter{
        void getAllCategories();
    }

    interface View{
        void renderRecyclerView(List<Category> categoryList);
        void itemClick(Category category,int position);
    }
}
