package com.example.hiringworkshop.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comments {
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("comment")
    @Expose
    private String comment;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
