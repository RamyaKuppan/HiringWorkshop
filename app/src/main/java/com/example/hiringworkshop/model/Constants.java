package com.example.hiringworkshop.model;

public interface Constants {

    interface HttpUrls{
        String VIDEO_URL = "https://hiringworkshop.herokuapp.com/api/workshop/video";
        String COMMENTS = "https://hiringworkshop.herokuapp.com/api/workshop/comments";
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
