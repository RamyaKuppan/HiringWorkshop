package com.example.hiringworkshop.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class User {

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COMMENT_ID = "commentId";

    @NonNull
    @ColumnInfo(name = COLUMN_COMMENT_ID)
    @PrimaryKey(autoGenerate = true)
    String commentId;

    @SerializedName("name")
    @ColumnInfo(name = COLUMN_NAME)
    String name;

    public User(@NonNull String commentId, String name) {
        this.commentId = commentId;
        this.name = name;
    }

    @Ignore
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(@NonNull String commentId) {
        this.commentId = commentId;
    }
}