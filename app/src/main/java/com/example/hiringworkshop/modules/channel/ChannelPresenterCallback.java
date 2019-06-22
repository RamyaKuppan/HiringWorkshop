package com.example.hiringworkshop.modules.channel;

import com.example.hiringworkshop.uimodels.ChannelDetail;
import com.example.hiringworkshop.mvp.PresenterToViewCallback;

public interface ChannelPresenterCallback extends PresenterToViewCallback {

    void showLoader();

    void showChannelDetail(ChannelDetail channelDetail);

    void showError();
}
