package com.example.hiringworkshop;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VideoApplication extends Application {

    private static VideoApplication mAppController;

    private RequestQueue requestQueue;

    public static synchronized VideoApplication getInstance() {
        return mAppController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppController = this;

    }


    public RequestQueue getRequestQueue() {
        return Volley.newRequestQueue(getApplicationContext());
    }

    public void addToRequestQueue(Request request, String tag) {

        request.setTag(tag);
        getRequestQueue().add(request);
    }
}
