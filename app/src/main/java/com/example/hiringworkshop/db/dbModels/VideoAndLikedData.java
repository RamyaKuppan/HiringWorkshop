package com.example.hiringworkshop.db.dbModels;

import com.example.hiringworkshop.models.LikedVideos;

import androidx.room.Embedded;

public class VideoAndLikedData {

    @Embedded
    VideoDbData videoDbData;

    @Embedded
    LikedVideos likedVideos;

    public VideoDbData getVideoDbData() {
        return videoDbData;
    }

    public void setVideoDbData(VideoDbData videoDbData) {
        this.videoDbData = videoDbData;
    }

    public LikedVideos getLikedVideos() {
        return likedVideos;
    }

    public void setLikedVideos(LikedVideos likedVideos) {
        this.likedVideos = likedVideos;
    }
}
