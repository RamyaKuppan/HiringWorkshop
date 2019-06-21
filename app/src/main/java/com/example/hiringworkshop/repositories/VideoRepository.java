package com.example.hiringworkshop.repositories;

import android.support.annotation.NonNull;

import com.example.hiringworkshop.models.ChannelDetail;
import com.example.hiringworkshop.models.VideoDetail;
import com.example.hiringworkshop.mvp.DataRepository;
import com.example.hiringworkshop.network.APIManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoRepository extends DataRepository {

    private static final VideoRepository instance = new VideoRepository();

    public static VideoRepository getInstance() {
        return instance;
    }

    private VideoDetail mVideoDetail;

    private ChannelDetail mChannelDetail;

    public void getMovieDetail(final MovieDetailCallback movieDetailCallback) {

        if (mVideoDetail != null && mChannelDetail != null) {
            movieDetailCallback.showDetail(mVideoDetail, mChannelDetail);
        } else {
            APIManager.getInstance().getApiService().getMovieDetail().enqueue(new Callback<VideoDetail>() {
                @Override
                public void onResponse(Call<VideoDetail> call, Response<VideoDetail> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        mVideoDetail = response.body();
                        mChannelDetail = new ChannelDetail();
                        mChannelDetail.setChannel(mVideoDetail.getChannel());
                        mChannelDetail.setChannelOwner(mVideoDetail.getChannelOwner());
                        mChannelDetail.setChannelSubscribers(mVideoDetail.getChannelSubscribers());
                        mChannelDetail.setSubscribed(MockRest.getInstance().getSubscribedChannels().contains(mChannelDetail));
                        mChannelDetail.setVideoDetails(new VideoDetail[]{mVideoDetail});
                        movieDetailCallback.showDetail(mVideoDetail, mChannelDetail);
                    } else {
                        movieDetailCallback.showError(response.code());
                    }
                }

                @Override
                public void onFailure(Call<VideoDetail> call, Throwable t) {
                    movieDetailCallback.showError(0);
                }
            });
        }
    }

    private void notifyVideoDetail() {
        setChanged();
        notifyObservers(mVideoDetail);
    }

    private void notifyVChannelDetail() {
        setChanged();
        notifyObservers(mChannelDetail);
    }

    @Override
    protected void clearData() {
        mVideoDetail = null;
        mChannelDetail = null;
    }

    public void toggleLike() {
        if (mVideoDetail != null) {
            if (MockRest.getInstance().getLikedVideos().contains(mVideoDetail)) {
                MockRest.getInstance().getLikedVideos().remove(mVideoDetail);
                mVideoDetail.setLiked(false);
            } else {
                MockRest.getInstance().getLikedVideos().add(mVideoDetail);
                mVideoDetail.setLiked(true);
            }

            notifyVideoDetail();
        }
    }

    public void toggleSubscription() {
        if (mChannelDetail != null) {
            if (MockRest.getInstance().getSubscribedChannels().contains(mChannelDetail)) {
                MockRest.getInstance().getSubscribedChannels().remove(mChannelDetail);
                mChannelDetail.setSubscribed(false);
            } else {
                MockRest.getInstance().getSubscribedChannels().add(mChannelDetail);
                mChannelDetail.setSubscribed(true);
            }

            notifyVChannelDetail();
        }
    }

    public interface MovieDetailCallback {

        void showDetail(@NonNull VideoDetail videoDetail, @NonNull ChannelDetail channelDetail);

        void showError(int responseCode);
    }
}
