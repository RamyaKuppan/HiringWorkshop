package com.example.hiringworkshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentsModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("replies")
    @Expose
    private List<RepliesModel> replies;

    @SerializedName("reply")
    @Expose
    private String reply;

    @SerializedName("timestamp")
    @Expose
    private long timestamp;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public List<RepliesModel> getReplies() {
        return replies;
    }

    public void setReplies(List<RepliesModel> replies) {
        this.replies = replies;
    }
}
