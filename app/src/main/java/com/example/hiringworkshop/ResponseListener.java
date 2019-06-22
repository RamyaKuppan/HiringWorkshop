package com.example.hiringworkshop;

import com.example.hiringworkshop.models.VideoModel;

import retrofit2.Response;

/**
 * ResponseListener.class
 * Network callback interface
 */
public interface ResponseListener {
    /**
     * on response received this method is used as the callback method
     *
     * @param response   response
     * @param identifier to identify the response received
     * @param <T>        template
     */
    <T> void onResponse(Response<T> response, String identifier);

    /**
     * on failure case happend this method will be invoked to notify the view
     */
    void onFailureResponse();

    void onDBDataReady(VideoModel videoModel);
}
