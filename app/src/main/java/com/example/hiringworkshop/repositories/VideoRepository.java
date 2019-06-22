package com.example.hiringworkshop.repositories;

import android.os.AsyncTask;

import com.example.hiringworkshop.db.VideoAppDbManager;
import com.example.hiringworkshop.db.dbModels.VideoAndLikedData;
import com.example.hiringworkshop.db.dbModels.VideoDbData;
import com.example.hiringworkshop.mvp.DataRepository;
import com.example.hiringworkshop.restApi.APIManager;
import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;
import com.example.hiringworkshop.uimodels.ChannelDetail;

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

        //TODO Check for connection and fallback to db.

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
                        //Fallbacking to get data from db.
                        getVideoDataFromDb(movieDetailCallback);
                    }
                }

                @Override
                public void onFailure(Call<VideoDetail> call, Throwable t) {

                    //Fallbacking to getData from db.
                    getVideoDataFromDb(movieDetailCallback);
                }
            });
        }
    }

    private void updateDataToDb(VideoDetail mVideoDetail, ChannelDetail mChannelDetail) {


        //TODO replace it with proper thread..
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                VideoDbData videoDbData = new VideoDbData();
                videoDbData.setChannelId(1L);
                videoDbData.setName(mVideoDetail.getDescription());
                videoDbData.setVideoImageUrl(mVideoDetail.getImage());
                videoDbData.setViews(mVideoDetail.getViews());

                VideoAppDbManager.getInstance().getDatabase().getVideoDao().insertVideo(videoDbData);
                return null;
            }
        }.execute();


//        AppAsyncTask appAsyncTask = new AppAsyncTask<String>(new AppAsyncTask.AppAsyncCallback<String>() {
//            @Override
//            public String doInBackGround() {
//
//                return "";
//            }
//
//            @Override
//            public void onPost(String aVoid) {
//
//            }
//        });
//
//        appAsyncTask.execute();

    }

    private void getVideoDataFromDb(MovieDetailCallback movieDetailCallback) {

        //TODO change it in future..
        new AsyncTask<Void, Void, List<VideoAndLikedData>>() {
            @Override
            protected List<VideoAndLikedData> doInBackground(Void... voids) {
                return VideoAppDbManager.getInstance().getDatabase().getVideoDao().getVideoDataList();
            }

            @Override
            protected void onPostExecute(List<VideoAndLikedData> videoAndLikedData) {
                super.onPostExecute(videoAndLikedData);

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
        }.execute();

//        AppAsyncTask<List<VideoAndLikedData>> appAsyncTask = new AppAsyncTask<List<VideoAndLikedData>>(new AppAsyncTask.AppAsyncCallback<List<VideoAndLikedData>>() {
//            @Override
//            public List<VideoAndLikedData> doInBackGround() {
//            }
//
//            @Override
//            public void onPost(List<VideoAndLikedData> videoAndLikedData) {
//
//
//            }
//        });
//
//        appAsyncTask.execute();
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

        //Update db operations pending..

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
