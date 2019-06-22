package com.example.hiringworkshop.db.dbModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "liked_videos")
public class LikedVideosDbData {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "video_id")
    private long videoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }
}
