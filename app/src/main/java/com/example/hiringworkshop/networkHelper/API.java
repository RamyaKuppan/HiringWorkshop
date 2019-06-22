package com.example.hiringworkshop.networkHelper;

import com.example.hiringworkshop.models.CommentsModel;
import com.example.hiringworkshop.models.VideoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    /**
     * call method for video description
     *
     * @return video details
     */
    @GET("api/workshop/video")
    Call<VideoModel> getVideoDescription();

    /**
     * call method for comments list
     *
     * @return list of comments
     */
    @GET("api/workshop/comments")
    Call<List<CommentsModel>> getCommentsList();

    /**
     * post comments
     *
     * @param commentsModel comments
     * @return response
     */
    @POST("api/workshop/comment")
    Call<CommentsModel> postComments(@Body CommentsModel commentsModel);


    @POST("api/workshop/reply")
    Call<CommentsModel> replyToAComment(@Body CommentsModel commentsModel);
}

