package com.forbitbd.quizapp.model;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private String _id;
    private String name;
    private String display_name;
    private List<SubCategory> subcats;

    public Category() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public List<SubCategory> getSubcats() {
        return subcats;
    }

    public void setSubcats(List<SubCategory> subcats) {
        this.subcats = subcats;
    }
}
