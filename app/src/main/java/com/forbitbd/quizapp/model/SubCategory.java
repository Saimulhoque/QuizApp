package com.forbitbd.quizapp.model;

import java.io.Serializable;

public class SubCategory implements Serializable {

    private String name;
    private String display_name;

    public SubCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
