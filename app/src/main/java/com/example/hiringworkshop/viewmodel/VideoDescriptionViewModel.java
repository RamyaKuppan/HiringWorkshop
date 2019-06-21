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

public class VideoDescriptionViewModel extends ViewModel {

    ResponseListener iResponseListener;

    private boolean isLiked;

    public void setResponseListener(ResponseListener iResponseListener) {
        this.iResponseListener = iResponseListener;
    }

    public void removeListener() {
        iResponseListener = null;
    }


    public void makeApiCall() {
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


    public boolean getLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
