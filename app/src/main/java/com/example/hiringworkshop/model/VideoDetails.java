package com.example.hiringworkshop.model;

public class VideoDetails {

    public static VideoDetails videoDetails;
    public String subscribe;

    public static VideoDetails getInstance() {
        if (videoDetails == null) {
            videoDetails = new VideoDetails();
            return videoDetails;
        }
        return videoDetails;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }
}
