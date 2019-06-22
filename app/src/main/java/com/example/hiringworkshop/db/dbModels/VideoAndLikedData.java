package com.example.hiringworkshop.db.dbModels;

import androidx.room.Embedded;

public class VideoAndLikedData {

    @Embedded
    VideoDbData videoDbData;

    @Embedded
    LikedVideosDbData likedVideos;

    public VideoDbData getVideoDbData() {
        return videoDbData;
    }

    public void setVideoDbData(VideoDbData videoDbData) {
        this.videoDbData = videoDbData;
    }

    public LikedVideosDbData getLikedVideos() {
        return likedVideos;
    }

    public void setLikedVideos(LikedVideosDbData likedVideos) {
        this.likedVideos = likedVideos;
    }
}
