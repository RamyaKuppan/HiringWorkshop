package com.example.hiringworkshop.networkHelper;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static Retrofit mRetrofit = null;

    private static RetrofitHelper mHelper = null;

    private API mApiImpl;

    private RetrofitHelper() {
        mApiImpl = getRetrofit().create(API.class);
    }

    /**
     * this method will create the retrofit instance
     *
     * @return retrofit instance
     */
    private static Retrofit getRetrofit() {
        if (null == mRetrofit) {

            int requestTimeout = 60;
            OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(requestTimeout, TimeUnit.SECONDS)
                    .readTimeout(requestTimeout, TimeUnit.SECONDS)
                    .writeTimeout(requestTimeout, TimeUnit.SECONDS);

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(interceptor);
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://hiringworkshop.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return mRetrofit;
    }

    /**
     * instance of retrofit helper
     *
     * @return retrofit helper
     */
    public static RetrofitHelper getInstance() {
        if (mHelper == null) {
            mHelper = new RetrofitHelper();
        }

        return mHelper;
    }

    /**
     * Returns a reference to implementation for the interface parameter passed.
     *
     * @return Implementation reference of the interface passed.
     */
    public API getAPI() {
        return mApiImpl;
    }
}

