package com.example.hiringworkshop.viewmodel;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.example.hiringworkshop.VideoApplication;
import com.example.hiringworkshop.adapter.CommentsAdapter;
import com.example.hiringworkshop.model.IWebserviceListener;
import com.example.hiringworkshop.model.WebserviceManager;
import com.example.hiringworkshop.model.response.CommentsData;
import com.example.hiringworkshop.model.response.VideoViewData;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static com.example.hiringworkshop.model.Constants.BundleKKey.VIDEO_DATA;
import static com.example.hiringworkshop.model.Constants.HttpUrls.COMMENTS;
import static com.example.hiringworkshop.model.Constants.HttpUrls.VIDEO_URL;

public class VideoViewModel extends ViewModel {

    private MutableLiveData<VideoViewData> videoViewData = new MutableLiveData<>();
    private MutableLiveData<List<CommentsData>> commentsData = new MutableLiveData<>();
    private MutableLiveData<Bundle> bundleVideoData = new MutableLiveData<>();
    private CommentsAdapter mAdapter;

    public void sendVideoDetailsRequest() {
        Type responseType = new TypeToken<VideoViewData>() {}.getType();
        WebserviceManager<VideoViewData> manager = new WebserviceManager<>(Request.Method.GET, VIDEO_URL, responseType, iVideoListener);
        VideoApplication.getInstance().addToRequestQueue(manager, "VideoDetails");
    }

    /**
     * VideoView API Webservice Callbacks
     */
    private IWebserviceListener<VideoViewData> iVideoListener = new IWebserviceListener<VideoViewData>() {
        @Override
        public void onSuccessfulApi(VideoViewData data) {

            getVideoViewData().postValue(data);

        }

        @Override
        public void onSuccessfulListApi(List<VideoViewData> response) {

        }

        @Override
        public void onFailureApi(String error) {
            Log.e("VideoDetailsActivity", "Error is : " + error);
        }
    };

    public void sendGetCommentsRequest() {
        Type responseType = new TypeToken<List<CommentsData>>() {}.getType();
        WebserviceManager<CommentsData> manager = new WebserviceManager<>(Request.Method.GET, COMMENTS, responseType, iCommentsListener);
        VideoApplication.getInstance().addToRequestQueue(manager, "GetComments");
    }

    /**
     * Comments API Webservice Callbacks
     */
    private IWebserviceListener<CommentsData> iCommentsListener = new IWebserviceListener<CommentsData>() {
        @Override
        public void onSuccessfulApi(CommentsData data) {

        }

        @Override
        public void onSuccessfulListApi(List<CommentsData> data) {
            getCommentsData().postValue(data);

        }

        @Override
        public void onFailureApi(String error) {
            Log.e("VideoDetailsActivity", "Error is : " + error);
        }
    };

    public void onClickHere(@NonNull VideoViewData data) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(VIDEO_DATA, data);
        getBundleVideoData().postValue(bundle);
    }

    public MutableLiveData<VideoViewData> getVideoViewData() {
        return videoViewData;
    }

    public MutableLiveData<Bundle> getBundleVideoData() {
        return bundleVideoData;
    }

    public MutableLiveData<List<CommentsData>> getCommentsData() {
        return commentsData;
    }
}
