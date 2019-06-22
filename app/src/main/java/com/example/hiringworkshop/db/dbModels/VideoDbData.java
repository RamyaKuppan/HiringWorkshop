package com.example.hiringworkshop.db.dbModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "video")
public class VideoDbData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vid")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "videoImageUrl")
    private String videoImageUrl;

    @ColumnInfo(name = "views")
    private String views;

    @ColumnInfo(name = "channel_id")
    private long channelId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        this.videoImageUrl = videoImageUrl;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}
