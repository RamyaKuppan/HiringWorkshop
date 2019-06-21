package com.example.hiringworkshop.modules.channel;

import android.support.annotation.NonNull;

import com.example.hiringworkshop.models.ChannelDetail;
import com.example.hiringworkshop.models.VideoDetail;
import com.example.hiringworkshop.mvp.Presenter;
import com.example.hiringworkshop.repositories.VideoRepository;

import java.util.Observable;

public class ChannelPresenter extends Presenter<ChannelPresenterCallback> {

    @Override
    protected void registerWithModel() {
        VideoRepository.getInstance().addObserver(this);
    }

    @Override
    protected void unregisterModels() {
        VideoRepository.getInstance().deleteObserver(this);
    }

    public void toggleSubscribe() {
        VideoRepository.getInstance().toggleSubscription();
    }

    public void getMovieDetail() {


        if (getCallback() != null) {
            getCallback().showLoader();
        }
        VideoRepository.getInstance().getMovieDetail(new VideoRepository.MovieDetailCallback() {
            @Override
            public void showDetail(@NonNull VideoDetail videoDetail, ChannelDetail channelDetail) {
                if (getCallback() != null) {
                    getCallback().showChannelDetail(channelDetail);
                }
            }

            @Override
            public void showError(int responseCode) {
                if (getCallback() != null) {
                    getCallback().showError();
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof VideoRepository && arg instanceof ChannelDetail) {
            if (getCallback() != null) {
                getCallback().showChannelDetail((ChannelDetail) arg);
            }
        }
    }
}
