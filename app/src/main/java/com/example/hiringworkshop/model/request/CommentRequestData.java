package com.example.hiringworkshop.model.request;

import com.google.gson.annotations.SerializedName;

public class CommentRequestData {
    @SerializedName("user")
    String user;
    @SerializedName("comment")
    String comment;

    public CommentRequestData(String name, String comment) {
        this.user = name;
        this.comment = comment;
    }

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
