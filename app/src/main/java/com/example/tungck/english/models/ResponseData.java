package com.example.tungck.english.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TungCK on 10/31/2015.
 */
public class ResponseData {
    @SerializedName("results")
    @Expose
    public List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
