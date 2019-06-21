package com.example.hiringworkshop.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoResponse {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("uploader")
    @Expose
    private String uploader;
    @SerializedName("views")
    @Expose
    private String views;
    @SerializedName("uploadedTimeline")
    @Expose
    private String uploadedTimeline;
    @SerializedName("channelSubscribers")
    @Expose
    private Integer channelSubscribers;
    @SerializedName("channelOwner")
    @Expose
    private String channelOwner;

    public String getIschannelSubscribed() {
        return ischannelSubscribed;
    }

    public void setIschannelSubscribed(String ischannelSubscribed) {
        this.ischannelSubscribed = ischannelSubscribed;
    }

    private String ischannelSubscribed;

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

    public Integer getChannelSubscribers() {
        return channelSubscribers;
    }

    public void setChannelSubscribers(Integer channelSubscribers) {
        this.channelSubscribers = channelSubscribers;
    }

    public String getChannelOwner() {
        return channelOwner;
    }

    public void setChannelOwner(String channelOwner) {
        this.channelOwner = channelOwner;
    }

}

