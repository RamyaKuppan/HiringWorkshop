package com.example.hiringworkshop.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("timestamp")
    @Expose
    private Double timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }
}
