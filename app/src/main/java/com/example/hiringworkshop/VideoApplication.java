package com.example.hiringworkshop;

import android.app.Application;

import com.example.hiringworkshop.model.User;
import com.example.hiringworkshop.network.RetrofitClient;

public class VideoApplication extends Application {

    private static RetrofitClient mRetrofitClient;
    private static User mUser;

    public static User getUserInfo() {
        return mUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mUser = new User("Sureshkumar");
        mRetrofitClient = RetrofitClient.getInstance();
    }

    public static RetrofitClient getRetrofitClient() {
        return mRetrofitClient;
    }
}
