package com.example.hiringworkshop.loaders;

import android.util.Log;

import com.example.hiringworkshop.api.RetrofitClient;
import com.example.hiringworkshop.api.VideoApi;
import com.example.hiringworkshop.api.VideoLoaderCallbacks;
import com.example.hiringworkshop.data.CommentDetail;
import com.example.hiringworkshop.data.VideoDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoLoader {
    private VideoLoaderCallbacks callbacks;
    private VideoApi service;

    public VideoLoader(VideoLoaderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.service = RetrofitClient.getRetrofitInstance().create(VideoApi.class);
    }

    public void loadVideo() {
        Call<VideoDetails> call = service.getVideo();
        call.enqueue(new Callback<VideoDetails>() {
            @Override
            public void onResponse(Call<VideoDetails> call, Response<VideoDetails> response) {
                VideoDetails data = response.body();
                callbacks.onVideoDetailLoaded(data);
            }

            @Override
            public void onFailure(Call<VideoDetails> call, Throwable t) {
            }
        });
    }

    public void getComments() {
        Call<List<CommentDetail>> call = service.getComments();
        call.enqueue(new Callback<List<CommentDetail>>() {
            @Override
            public void onResponse(Call<List<CommentDetail>> call, Response<List<CommentDetail>> response) {
                List<CommentDetail> data = response.body();
                callbacks.onCommentsLoaded(data);
            }

            @Override
            public void onFailure(Call<List<CommentDetail>> call, Throwable t) {
            }
        });
    }

    public void postComment(String comment) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), getPostJson(comment).toString());

        Call<List<CommentDetail>> call = service.postComment(requestBody);
        call.enqueue(new Callback<List<CommentDetail>>() {
            @Override
            public void onResponse(Call<List<CommentDetail>> call, Response<List<CommentDetail>> response) {
                List<CommentDetail> data = response.body();
                callbacks.onCommentsLoaded(data);
            }

            @Override
            public void onFailure(Call<List<CommentDetail>> call, Throwable t) {
            }
        });
    }

    private JSONObject getPostJson(String comment) {
        JSONObject post = new JSONObject();
        try {
            post.put("user", "sabari");
            post.put("comment", comment);
        } catch (JSONException e) {
            Log.e("ex", "ex");
        }
        return post;
    }
}
