package com.example.hiringworkshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepliesModel {

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("message")
    @Expose
    private String message;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
