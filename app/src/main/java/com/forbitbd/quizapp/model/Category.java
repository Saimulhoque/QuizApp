package com.forbitbd.quizapp.model;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private String _id;
    private String name;
    private List<String> subcats;

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

    public List<String> getSubcats() {
        return subcats;
    }

    public void setSubcats(List<String> subcats) {
        this.subcats = subcats;
    }
}
