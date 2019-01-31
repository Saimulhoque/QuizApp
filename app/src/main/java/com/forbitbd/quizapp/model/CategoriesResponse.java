package com.forbitbd.quizapp.model;

import java.util.List;

public class CategoriesResponse {

    private int count;
    private List<Category> categories;


    public CategoriesResponse() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
