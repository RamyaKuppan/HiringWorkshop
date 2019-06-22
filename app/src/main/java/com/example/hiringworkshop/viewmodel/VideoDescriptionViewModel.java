package com.example.hiringworkshop.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.hiringworkshop.ResponseListener;
import com.example.hiringworkshop.models.CommentsModel;
import com.example.hiringworkshop.models.VideoModel;
import com.example.hiringworkshop.networkHelper.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * VideoDescriptionViewModel.java
 * This class is for performing business related operations for the vide description view
 */
public class VideoDescriptionViewModel extends ViewModel {

    ResponseListener iResponseListener;

    private boolean isLiked;

    /**
     * on the activity start the listeners will be added to send the call back to the view
     *
     * @param iResponseListener response listener
     */
    public void setResponseListener(ResponseListener iResponseListener) {
        this.iResponseListener = iResponseListener;
    }

    /**
     * when the activity is destroyed the listeners will be removed
     */
    public void removeListener() {
        iResponseListener = null;
    }

    /**
     * this method helps to get the video details from the API
     */
    public void getVideoDetails() {
        RetrofitHelper.getInstance().getAPI().getVideoDescription().enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(@NonNull Call<VideoModel> call, @NonNull retrofit2.Response<VideoModel> response) {
                if (iResponseListener != null && response.isSuccessful()) {
                    iResponseListener.onResponse(response, "Video Detail");
                    getListOfComments();
                }
            }

            @Override
            public void onFailure(@NonNull Call<VideoModel> call,
                                  @NonNull Throwable t) {
                if (iResponseListener != null) {
                    iResponseListener.onFailureResponse();
                }
            }
        });

    }

    /**
     * list of video comments
     */
    private void getListOfComments() {
        RetrofitHelper.getInstance().getAPI().getCommentsList().enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CommentsModel>> call,
                                   @NonNull Response<List<CommentsModel>> response) {
                if (iResponseListener != null && response.isSuccessful()) {
                    iResponseListener.onResponse(response, "comments");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CommentsModel>> call,
                                  @NonNull Throwable t) {
                if (iResponseListener != null)
                    iResponseListener.onFailureResponse();
            }
        });
    }

    /**
     * get liked info
     *
     * @return true if liked or false
     */
    public boolean getLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    /**
     * upload video comments
     *
     * @param comments comments
     */
    public void uploadComments(String comments) {
        CommentsModel commentsModel = new CommentsModel();
        commentsModel.setComment(comments);
        RetrofitHelper.getInstance().getAPI().postComments(commentsModel).enqueue(new Callback<CommentsModel>() {
            @Override
            public void onResponse(@NonNull Call<CommentsModel> call, @NonNull Response<CommentsModel> response) {
                if (iResponseListener != null && response.isSuccessful()) {
                    iResponseListener.onResponse(response, "uploadcomments");
                } else if (iResponseListener != null)
                    iResponseListener.onFailureResponse();
            }

            @Override
            public void onFailure(@NonNull Call<CommentsModel> call, @NonNull Throwable t) {
                if (iResponseListener != null)
                    iResponseListener.onFailureResponse();
            }

        });

    }
}
