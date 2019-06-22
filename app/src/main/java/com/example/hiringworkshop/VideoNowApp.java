package com.example.hiringworkshop;

import android.app.Application;
import android.content.Context;

import com.example.hiringworkshop.log.TimberThreadDebugTree;

import timber.log.Timber;

public class VideoNowApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new TimberThreadDebugTree());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
