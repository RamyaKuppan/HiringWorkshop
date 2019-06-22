package com.example.hiringworkshop.db.dbModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "comment_reply")
public class CommentReplyDbData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rid")
    private long id;

    @ColumnInfo(name = "comment_id")
    private String commentId;

    @ColumnInfo(name = "reply")
    private String reply;

    @ColumnInfo(name = "userName")
    private String userName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
