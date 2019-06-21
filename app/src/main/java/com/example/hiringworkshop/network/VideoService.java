package com.example.hiringworkshop.network;

import com.example.hiringworkshop.model.Details;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VideoService {
    @GET("workshop/video")
    Call<JSONObject> getDetails();

    @GET("workshop/comments")
    Call<JSONObject> getComments();

    @POST("workshop/Comment")
    Call<JSONObject> postComment();

}
