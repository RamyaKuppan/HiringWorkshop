package com.example.hiringworkshop.network;

import com.example.hiringworkshop.models.VideoComment;
import com.example.hiringworkshop.models.VideoDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @GET("workshop/video")
    Call<VideoDetail> getMovieDetail();

    @GET("workshop/video/comments")
    Call<List<VideoComment>> getVideoComments();

    @POST("workshop/video/comments")
    Call<List<VideoComment>> uploadVideoComment(@Body VideoComment videoComment);
}
