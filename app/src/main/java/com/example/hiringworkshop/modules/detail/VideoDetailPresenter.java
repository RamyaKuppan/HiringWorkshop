package com.example.hiringworkshop.modules.detail;

import android.support.annotation.NonNull;

import com.example.hiringworkshop.models.ChannelDetail;
import com.example.hiringworkshop.models.VideoComment;
import com.example.hiringworkshop.models.VideoDetail;
import com.example.hiringworkshop.mvp.Presenter;
import com.example.hiringworkshop.repositories.VideoCommentsRepository;
import com.example.hiringworkshop.repositories.VideoRepository;

import java.util.List;
import java.util.Observable;

public class VideoDetailPresenter extends Presenter<DetailPresenterCallback> {

    @Override
    protected void registerWithModel() {
        VideoRepository.getInstance().addObserver(this);
        VideoCommentsRepository.getInstance().addObserver(this);
    }

    @Override
    protected void unregisterModels() {
        VideoRepository.getInstance().deleteObserver(this);
        VideoCommentsRepository.getInstance().deleteObserver(this);
    }

    public void toggleLike() {
        VideoRepository.getInstance().toggleLike();
    }

    public void toggleSubscribe() {
        VideoRepository.getInstance().toggleSubscription();
    }

    public void getMovieDetail() {
        if (getCallback() != null) {
            getCallback().showVideoLoading();
        }
        VideoRepository.getInstance().getMovieDetail(new VideoRepository.MovieDetailCallback() {
            @Override
            public void showDetail(@NonNull VideoDetail videoDetail, @NonNull ChannelDetail channelDetail) {
                if (getCallback() != null) {
                    getCallback().showMovieDetail(videoDetail);
                    getCallback().showChannelInfo(channelDetail);
                }
            }

            @Override
            public void showError(int responseCode) {
                if (getCallback() != null) {
                    getCallback().showVideoFetchingError();
                }
            }
        });
    }

    public void getComments() {
        if (getCallback() != null) {
            getCallback().showVideoLoading();
        }
        VideoCommentsRepository.getInstance().getVideoComments(new VideoCommentsRepository.VideoCommentsCallback() {

            @Override
            public void showVideComments(List<VideoComment> videoCommentList) {
                if (getCallback() != null) {
                    getCallback().showLoaderOnCommentsSection();
                    getCallback().showComments(videoCommentList);
                }
            }

            @Override
            public void showError(int responseCode) {
                if (getCallback() != null) {
                    getCallback().showCommentsError();
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof VideoRepository) {
            if (arg instanceof VideoDetail) {
                if (getCallback() != null) {
                    getCallback().showMovieDetail((VideoDetail) arg);
                }
            } else if (arg instanceof ChannelDetail) {
                if (getCallback() != null) {
                    getCallback().showChannelInfo((ChannelDetail) arg);
                }
            }
        }
    }
}
