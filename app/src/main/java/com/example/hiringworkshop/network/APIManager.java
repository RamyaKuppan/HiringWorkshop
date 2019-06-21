package com.example.hiringworkshop.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

    private static final APIManager instance = new APIManager();

    private Retrofit retrofit;

    private APIService apiService;

    public static APIManager getInstance() {
        return instance;
    }

    private APIManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://hiringworkshop.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public APIService getApiService() {
        return apiService;
    }
}
