package com.example.hiringworkshop.model;

import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("channel")
    String channel;

    @SerializedName("channelSubscribers")
    String channelSubscribers;

    @SerializedName("channelOwner")
    String channelOwner;

    @SerializedName("isSubscribed")
    boolean isSubscribed;

    public String getChannel() {
        return channel;
    }

    public String getChannelSubscribers() {
        return channelSubscribers;
    }

    public String getChannelOwner() {
        return channelOwner;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setChannelSubscribers(String channelSubscribers) {
        this.channelSubscribers = channelSubscribers;
    }

    public void setChannelOwner(String channelOwner) {
        this.channelOwner = channelOwner;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
}
