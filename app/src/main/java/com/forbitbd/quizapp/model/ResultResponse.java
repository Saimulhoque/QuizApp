package com.forbitbd.quizapp.model;

import java.util.List;

public class ResultResponse {

    private int count;
    private List<PostResult> results;

    public ResultResponse() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PostResult> getResults() {
        return results;
    }

    public void setResults(List<PostResult> results) {
        this.results = results;
    }
}
