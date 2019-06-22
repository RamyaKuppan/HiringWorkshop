package com.example.hiringworkshop;

import android.app.Application;
import android.content.Context;

import com.example.hiringworkshop.model.User;
import com.example.hiringworkshop.network.RetrofitClient;

public class VideoApplication extends Application {

    private static RetrofitClient mRetrofitClient;
    private static User mUser;
    private static Context mContext;

    public static User getUserInfo() {
        return mUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mUser = new User("Sureshkumar");
        mRetrofitClient = RetrofitClient.getInstance();
        mContext = this;
    }

    public static RetrofitClient getRetrofitClient() {
        return mRetrofitClient;
    }

    public static Context getContext() {
        return mContext;
    }

}
