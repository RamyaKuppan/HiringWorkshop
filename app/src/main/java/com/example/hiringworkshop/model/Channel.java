package com.example.hiringworkshop.model;

public class Channel {

    String channel;
    long channelSubscribers;
    String channelOwner;
    boolean isSubscribed;

    public String getChannel() {
        return channel;
    }

    public String getChannelSubscribers() {
        return String.valueOf(channelSubscribers);
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

    public void setChannelSubscribers(long channelSubscribers) {
        this.channelSubscribers = channelSubscribers;
    }

    public void setChannelOwner(String channelOwner) {
        this.channelOwner = channelOwner;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
}
