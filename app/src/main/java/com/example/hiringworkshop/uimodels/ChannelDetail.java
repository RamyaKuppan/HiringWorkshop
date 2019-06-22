package com.example.hiringworkshop.uimodels;

import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;

public class ChannelDetail {

    //TODO add serializations.
    private String channel;

    private int channelSubscribers;

    private String channelOwner;

    private boolean isSubscribed;

    private VideoDetail[] videoDetails;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getChannelSubscribers() {
        return channelSubscribers;
    }

    public void setChannelSubscribers(int channelSubscribers) {
        this.channelSubscribers = channelSubscribers;
    }

    public String getChannelOwner() {
        return channelOwner;
    }

    public void setChannelOwner(String channelOwner) {
        this.channelOwner = channelOwner;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    public VideoDetail[] getVideoDetails() {
        return videoDetails;
    }

    public void setVideoDetails(VideoDetail[] videoDetails) {
        this.videoDetails = videoDetails;
    }

    @Override
    public int hashCode() {
        return channel.hashCode();
    }
}
