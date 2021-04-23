package com.assign.photosearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultModel {

    @SerializedName("total")
    private String total;
    @SerializedName("total_pages")
    private String total_pages;
    @SerializedName("results")
    private List<Result> results;

    public ResultModel() {}

    public ResultModel(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }
}


