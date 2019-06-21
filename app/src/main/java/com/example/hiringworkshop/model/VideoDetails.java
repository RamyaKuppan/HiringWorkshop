package com.example.hiringworkshop.model;

public class VideoDetails {

    public static VideoDetails videoDetails;
    public boolean isSubscribed;

    public static VideoDetails getInstance() {
        if (videoDetails == null) {
            videoDetails = new VideoDetails();
            return videoDetails;
        }
        return videoDetails;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
}
