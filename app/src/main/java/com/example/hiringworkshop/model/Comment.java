package com.example.hiringworkshop.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    String id;
    User user;
    @SerializedName("comment")
    String comment;
    @SerializedName("timestamp")
    long timestamp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
