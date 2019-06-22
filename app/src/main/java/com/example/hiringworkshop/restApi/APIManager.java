package com.example.hiringworkshop.restApi;

import com.example.hiringworkshop.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

    private static final APIManager instance = new APIManager();

    private APIService apiService;

    public static APIManager getInstance() {
        return instance;
    }

    private APIManager() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(loggingInterceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hiringworkshop.herokuapp.com/api/") //TODO get it from properties file.
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public APIService getApiService() {
        return apiService;
    }
}
