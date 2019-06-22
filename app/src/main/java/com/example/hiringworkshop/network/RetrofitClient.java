package com.example.hiringworkshop.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mInstance;
    private static VideoService mService;


    private RetrofitClient() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("http://hiringworkshop.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = mRetrofit.create(VideoService.class);
    }

    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public VideoService getService() {
        return mService;
    }

}
