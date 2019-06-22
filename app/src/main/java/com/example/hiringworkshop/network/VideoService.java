package com.example.hiringworkshop.network;

import com.example.hiringworkshop.model.Comment;
import com.example.hiringworkshop.model.request.CommentRequestData;
import com.example.hiringworkshop.model.response.CommentResponseData;
import com.example.hiringworkshop.model.response.DetailsResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VideoService {

    @GET("workshop/video")
    Call<DetailsResponseData> getDetails();

    @GET("workshop/comments")
    Call<List<CommentResponseData>> getComments();

    @Headers("Content-Type: application/json")
    @POST("workshop/comment")
    Call<Object> postComment(@Body CommentRequestData data);

}
