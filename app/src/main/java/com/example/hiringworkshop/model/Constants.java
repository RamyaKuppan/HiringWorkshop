package com.example.hiringworkshop.model;

public interface Constants {

    interface HttpUrls{
        String URL = "https://hiringworkshop.herokuapp.com/api/workshop/video";
    }

    interface BundleKKey{
        String OPINION = "OPINION";
        String VIDEO_DATA = "VIDEO_DATA";
        String IS_CHANNEL_SUBSCRIBE = "IS_CHANNEL_SUBSCRIBE";
    }

    interface RequestCodes{
        int SUBSCRIBE_REQUEST = 101;
    }
}
