package com.example.hiringworkshop.db.dbModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "video_comments")
public class VideoCommentsDbData {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "video_id")
    private long videoId;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "comment")
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
