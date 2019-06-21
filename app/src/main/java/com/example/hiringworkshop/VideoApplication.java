package com.example.hiringworkshop;

import android.app.Application;

import com.example.hiringworkshop.network.RetrofitClient;

public class VideoApplication extends Application {

    private static RetrofitClient mRetrofitClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mRetrofitClient = RetrofitClient.getInstance();
    }

    public static RetrofitClient getRetrofitClient() {
        return mRetrofitClient;
    }
}
