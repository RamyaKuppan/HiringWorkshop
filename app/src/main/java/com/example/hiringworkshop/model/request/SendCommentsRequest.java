package com.example.hiringworkshop.model.request;

public class SendCommentsRequest {

    private String user;
    private String comment;

    public SendCommentsRequest(String user, String comments) {
        this.user = user;
        this.comment = comments;
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
