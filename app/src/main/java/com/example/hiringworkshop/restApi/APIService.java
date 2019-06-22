package com.example.hiringworkshop.restApi;

import com.example.hiringworkshop.restApi.restApiModels.CommentReply;
import com.example.hiringworkshop.restApi.restApiModels.VideoComment;
import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @GET("workshop/video")
    Call<VideoDetail> getMovieDetail();

    @GET("workshop/comments")
    Call<List<VideoComment>> getVideoComments();

    @POST("workshop/comments")
    Call<List<VideoComment>> uploadVideoComment(@Body VideoComment videoComment);

    @POST("workshop/reply")
    Call addReplyToComment(CommentReply commentReply);
}
