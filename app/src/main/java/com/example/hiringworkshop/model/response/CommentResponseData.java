package com.example.hiringworkshop.model.response;

import androidx.annotation.Nullable;

import com.example.hiringworkshop.model.User;
import com.google.gson.annotations.SerializedName;

public class CommentResponseData {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("comment")
    String comment;
    @SerializedName("timestamp")
    long timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
