package com.example.hiringworkshop.uimodels;

import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;

import java.util.List;

public class LikedVideos {

    //TODO add serialization
    private List<VideoDetail> likedVideos;

    public List<VideoDetail> getLikedVideos() {
        return likedVideos;
    }

    public void setLikedVideos(List<VideoDetail> likedVideos) {
        this.likedVideos = likedVideos;
    }
}
