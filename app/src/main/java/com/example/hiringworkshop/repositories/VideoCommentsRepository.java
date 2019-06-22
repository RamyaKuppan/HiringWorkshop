package com.example.hiringworkshop.repositories;

import com.example.hiringworkshop.mvp.DataRepository;
import com.example.hiringworkshop.restApi.APIManager;
import com.example.hiringworkshop.restApi.restApiModels.CommentReply;
import com.example.hiringworkshop.restApi.restApiModels.VideoComment;

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

        //TODO Check for connection and fallback to db.


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

                        //TODO wire it with DB to fallback to offline data.
                        videoCommentsCallback.showError(response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<VideoComment>> call, Throwable t) {
                    //TODO wire it with DB to fallback to offline data.
                    videoCommentsCallback.showError(0);
                }
            });
        }
    }

    public void addReplyToComment(String commentId, String reply) {

        CommentReply commentReply = new CommentReply();
        commentReply.setId(commentId);
        commentReply.setReply(reply);
        commentReply.setUser("Prasanna");

        APIManager.getInstance().getApiService().addReplyToComment(commentReply).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response != null && response.isSuccessful()) {
                    //TODO done.
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //TODO handle failure.
            }
        });
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
                    if (videoCommentsCallback != null) {
                        videoCommentsCallback.showError(response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VideoComment>> call, Throwable t) {
                if (videoCommentsCallback != null) {
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

    public VideoComment getRepliesForComment(String commentId) {
        for (VideoComment videoComment : mVideoComments) {
            if (videoComment.getName().equals(commentId)) {

            }
        }

        return null;
    }

    public void sendReply(String reply, String commentId) {

        CommentReply commentReply = new CommentReply();
        commentReply.setUser("Prasanna");
        commentReply.setReply(reply);
        commentReply.setId(commentId);

        APIManager.getInstance().getApiService().addReplyToComment(commentReply).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                //TODO
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //TODO.
            }
        });
    }

    public interface VideoCommentsCallback {

        void showVideComments(List<VideoComment> videoCommentList);

        void showError(int responseCode);
    }
}
