package com.example.hiringworkshop.modules.detail;

import com.example.hiringworkshop.models.ChannelDetail;
import com.example.hiringworkshop.restApi.restApiModels.VideoComment;
import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;
import com.example.hiringworkshop.mvp.PresenterToViewCallback;

import java.util.List;

public interface DetailPresenterCallback extends PresenterToViewCallback {

    void showVideoLoading();

    void showMovieDetail(VideoDetail videoDetail);

    void showVideoFetchingError();

    void showChannelInfo(ChannelDetail channelDetail);

    void showLoaderOnCommentsSection();

    void showComments(List<VideoComment> videoCommentList);

    void showCommentsError();
}
