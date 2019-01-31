package com.forbitbd.quizapp.ui.main;

import com.forbitbd.quizapp.model.Category;

import java.util.List;

public interface MainContract {

    interface Presenter{
        void getAllCategories();
    }

    interface View{
        void renderRecyclerView(List<Category> categoryList);
        void itemClick(Category category);
    }
}
