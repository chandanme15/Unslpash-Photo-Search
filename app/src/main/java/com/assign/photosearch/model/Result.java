package com.assign.photosearch.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("description")
    private String description;

    @SerializedName("user")
    private User user;

    @SerializedName("urls")
    private URL urls;

    public Result() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User users) {
        this.user = users;
    }

    public URL getUrls() {
        return urls;
    }

    public void setUrls(URL urls) {
        this.urls = urls;
    }

}

