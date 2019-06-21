package com.example.hiringworkshop.api;

import com.example.hiringworkshop.data.CommentDetail;
import com.example.hiringworkshop.data.VideoDetails;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VideoApi {
    String API_VIDEO_DETAIL = "api/workshop/video";
    String API_COMMENTS = "api/workshop/comments";
    String API_POST_COMMENT = "api/workshop/comment";

    @GET(API_VIDEO_DETAIL)
    Call<VideoDetails> getVideo();

    @GET(API_COMMENTS)
    Call<List<CommentDetail>> getComments();

    @POST(API_POST_COMMENT)
    Call<List<CommentDetail>> postComment(@Body RequestBody var1);
}
