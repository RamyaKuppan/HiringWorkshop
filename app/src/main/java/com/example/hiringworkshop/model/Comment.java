package com.example.hiringworkshop.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class Comment {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    @SerializedName("id")
    String id;
    @ColumnInfo(name = COLUMN_COMMENT)
    @SerializedName("comment")
    String comment;
    @ColumnInfo(name = COLUMN_TIMESTAMP)
    @SerializedName("timestamp")
    String timestamp;

//    User user;

    public User getUser() {
        // todo.. get it from user, using commentId..
//        return user;
        return null;
    }

    public void setUser(User user) {
//        this.user = user;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = String.valueOf(timestamp);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
