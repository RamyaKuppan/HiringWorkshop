package com.example.hiringworkshop.models;

import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;

import java.util.List;

public class LikedVideos {

    private List<VideoDetail> likedVideos;

    public List<VideoDetail> getLikedVideos() {
        return likedVideos;
    }

    public void setLikedVideos(List<VideoDetail> likedVideos) {
        this.likedVideos = likedVideos;
    }
}
