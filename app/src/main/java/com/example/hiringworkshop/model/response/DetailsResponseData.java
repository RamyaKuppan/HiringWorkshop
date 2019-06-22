package com.example.hiringworkshop.model.response;

import com.google.gson.annotations.SerializedName;

public class DetailsResponseData {
    @SerializedName("image")
    String image;
    @SerializedName("description")
    String description;
    @SerializedName("channel")
    String channel;
    @SerializedName("uploader")
    String uploader;
    @SerializedName("views")
    String views;
    @SerializedName("uploadedTimeline")
    String uploadedTimeline;
    @SerializedName("channelSubscribers")
    long channelSubscribers;
    @SerializedName("channelOwner")
    String channelOwner;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getUploadedTimeline() {
        return uploadedTimeline;
    }

    public void setUploadedTimeline(String uploadedTimeline) {
        this.uploadedTimeline = uploadedTimeline;
    }

    public long getChannelSubscribers() {
        return channelSubscribers;
    }

    public void setChannelSubscribers(long channelSubscribers) {
        this.channelSubscribers = channelSubscribers;
    }

    public String getChannelOwner() {
        return channelOwner;
    }

    public void setChannelOwner(String channelOwner) {
        this.channelOwner = channelOwner;
    }
}
