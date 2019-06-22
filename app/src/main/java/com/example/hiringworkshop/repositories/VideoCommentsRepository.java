package com.example.hiringworkshop.repositories;

import com.example.hiringworkshop.restApi.restApiModels.VideoComment;
import com.example.hiringworkshop.mvp.DataRepository;
import com.example.hiringworkshop.restApi.APIManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoCommentsRepository extends DataRepository {

    private static final VideoCommentsRepository instance = new VideoCommentsRepository();

    public static VideoCommentsRepository getInstance() {
        return instance;
    }

    private List<VideoComment> mVideoComments;

    public void getVideoComments(final VideoCommentsCallback videoCommentsCallback) {

        if (mVideoComments != null) {
            videoCommentsCallback.showVideComments(mVideoComments);
        } else {
            APIManager.getInstance().getApiService().getVideoComments().enqueue(new Callback<List<VideoComment>>() {
                @Override
                public void onResponse(Call<List<VideoComment>> call, Response<List<VideoComment>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        mVideoComments = response.body();
                        videoCommentsCallback.showVideComments(mVideoComments);
                    } else {
                        videoCommentsCallback.showError(response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<VideoComment>> call, Throwable t) {
                    videoCommentsCallback.showError(0);
                }
            });
        }
    }

    public void addComment(final VideoComment videoComment, @Nullable final VideoCommentsCallback videoCommentsCallback) {

        if (mVideoComments == null) {
            mVideoComments = new ArrayList<>();
        }
        mVideoComments.add(videoComment);
        notifyVideoComments();
        APIManager.getInstance().getApiService().uploadVideoComment(videoComment).enqueue(new Callback<List<VideoComment>>() {
            @Override
            public void onResponse(Call<List<VideoComment>> call, Response<List<VideoComment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mVideoComments = response.body();
                    if (videoCommentsCallback != null) {
                        videoCommentsCallback.showVideComments(mVideoComments);
                    }
                } else {
                    if(videoCommentsCallback != null) {
                        videoCommentsCallback.showError(response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VideoComment>> call, Throwable t) {
                if(videoCommentsCallback != null) {
                    videoCommentsCallback.showError(0);
                }
            }
        });
    }

    private void notifyVideoComments() {
        setChanged();
        notifyObservers(mVideoComments);
    }

    @Override
    protected void clearData() {
        mVideoComments = null;
    }

    public interface VideoCommentsCallback {

        void showVideComments(List<VideoComment> videoCommentList);

        void showError(int responseCode);
    }
}
