package com.example.myapplication.model;

import java.util.List;


public class GankResponse {
    private boolean error;
    private List<GankArticle> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankArticle> getResults() {
        return results;
    }

    public void setResults(List<GankArticle> results) {
        this.results = results;
    }
}


