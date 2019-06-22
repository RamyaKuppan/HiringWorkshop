package com.example.hiringworkshop.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class Channel {

    public static final String COLUMN_DEVICE_ID = "deviceId";
    public static final String COLUMN_CHANNEL = "channel";
    public static final String COLUMN_CHANNEL_SUBSCRIBERS = "channel_subscribers";
    public static final String COLUMN_CHANNEL_OWNER = "channel_owner";
    public static final String COLUMN_IS_SUBSCRIBED = "is_subscribed";

    @NonNull
    @ColumnInfo(name = COLUMN_DEVICE_ID)
    @PrimaryKey
    String detailsId;

    @SerializedName("channel")
    @ColumnInfo(name = COLUMN_CHANNEL)
    String channel;

    @ColumnInfo(name = COLUMN_CHANNEL_SUBSCRIBERS)
    @SerializedName("channelSubscribers")
    String channelSubscribers;

    @ColumnInfo(name = COLUMN_CHANNEL_OWNER)
    @SerializedName("channelOwner")
    String channelOwner;

    @ColumnInfo(name = COLUMN_IS_SUBSCRIBED)
    @SerializedName("isSubscribed")
    int isSubscribed;

    public Channel(@NonNull String detailsId, String channel, String channelSubscribers, String channelOwner, int isSubscribed) {
        this.detailsId = detailsId;
        this.channel = channel;
        this.channelSubscribers = channelSubscribers;
        this.channelOwner = channelOwner;
        this.isSubscribed = isSubscribed;
    }

    @NonNull
    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(@NonNull String detailsId) {
        this.detailsId = detailsId;
    }

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
        return isSubscribed == 1;
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
        isSubscribed = subscribed ? 1 : 0;
    }
}
