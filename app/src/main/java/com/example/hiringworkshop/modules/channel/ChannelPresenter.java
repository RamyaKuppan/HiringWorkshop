package com.example.hiringworkshop.modules.channel;

import com.example.hiringworkshop.uimodels.ChannelDetail;
import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;
import com.example.hiringworkshop.mvp.Presenter;
import com.example.hiringworkshop.repositories.VideoRepository;

import java.util.Observable;

import androidx.annotation.NonNull;

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
