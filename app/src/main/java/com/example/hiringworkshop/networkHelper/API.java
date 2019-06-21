package com.example.hiringworkshop.networkHelper;

import com.example.hiringworkshop.models.CommentsModel;
import com.example.hiringworkshop.models.VideoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("api/workshop/video")
    Call<VideoModel> getVideoDescription();

    @GET("api/workshop/comments")
    Call<List<CommentsModel>> getCommentsList();
}

