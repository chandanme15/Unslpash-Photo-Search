package com.assign.photosearch.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class URL implements Serializable {

    @SerializedName("regular")
    private String regular;

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
