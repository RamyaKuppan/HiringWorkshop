package com.example.hiringworkshop.repositories;

import com.example.hiringworkshop.db.VideoAppDbManager;
import com.example.hiringworkshop.db.dbModels.VideoAndLikedData;
import com.example.hiringworkshop.db.dbModels.VideoDbData;
import com.example.hiringworkshop.models.ChannelDetail;
import com.example.hiringworkshop.mvp.DataRepository;
import com.example.hiringworkshop.restApi.APIManager;
import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;
import com.example.hiringworkshop.threads.AppAsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
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

                        updateDataToDb(mVideoDetail, mChannelDetail);

                    } else {
                        getVideoDataFromDb(movieDetailCallback);
                    }
                }

                @Override
                public void onFailure(Call<VideoDetail> call, Throwable t) {
                    getVideoDataFromDb(movieDetailCallback);
                }
            });
        }
    }

    private void updateDataToDb(VideoDetail mVideoDetail, ChannelDetail mChannelDetail) {

        AppAsyncTask appAsyncTask = new AppAsyncTask<Void>(new AppAsyncTask.AppAsyncCallback<Void>() {
            @Override
            public Void doInBackGround() {
                VideoDbData videoDbData = new VideoDbData();
                videoDbData.setChannelId(1L);
                videoDbData.setName(mVideoDetail.getDescription());
                videoDbData.setVideoImageUrl(mVideoDetail.getImage());
                videoDbData.setViews(mVideoDetail.getViews());

                VideoAppDbManager.getInstance().getDatabase().getVideoDao().insertVideo(videoDbData);
                return null;
            }

            @Override
            public void onPost(Void aVoid) {

            }
        });

        appAsyncTask.execute();

    }

    private void getVideoDataFromDb(MovieDetailCallback movieDetailCallback) {

        AppAsyncTask<List<VideoAndLikedData>> appAsyncTask = new AppAsyncTask<List<VideoAndLikedData>>(new AppAsyncTask.AppAsyncCallback<List<VideoAndLikedData>>() {
            @Override
            public List<VideoAndLikedData> doInBackGround() {
                return VideoAppDbManager.getInstance().getDatabase().getVideoDao().getVideoDataList();
            }

            @Override
            public void onPost(List<VideoAndLikedData> videoAndLikedData) {
                if (videoAndLikedData != null && videoAndLikedData.size() > 0) {
                    VideoDetail videoDetail = new VideoDetail();
                    videoDetail.setDescription(videoAndLikedData.get(0).getVideoDbData().getName());
                    videoDetail.setChannel(videoAndLikedData.get(0).getVideoDbData().getName());

                    ChannelDetail channelDetail = new ChannelDetail();
                    channelDetail.setChannel("bla bla");
                    if (movieDetailCallback != null) {
                        movieDetailCallback.showDetail(videoDetail, channelDetail);
                    }
                } else {
                    if (movieDetailCallback != null) {
                        movieDetailCallback.showError(0);
                    }
                }

            }
        });

        appAsyncTask.execute();
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
