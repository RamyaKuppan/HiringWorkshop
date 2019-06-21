package com.example.hiringworkshop.model;

public class Comment {

    String id;
    String user;
    String comment;
    long timestamp;


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
