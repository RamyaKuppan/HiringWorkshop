package com.example.hiringworkshop.model;

import com.google.gson.annotations.SerializedName;

public class Details {
    @SerializedName("image")
    String image;
    @SerializedName("description")
    String description;

    @SerializedName("uploader")
    String uploader;
    @SerializedName("views")
    String views;
    @SerializedName("uploadedTimeline")
    String uploadedTimeline;

    Channel channelInfo;

    boolean isLiked;

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

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

    public Channel getChannelInfo() {
        return channelInfo;
    }

    public void setChannelInfo(Channel channelInfo) {
        this.channelInfo = channelInfo;
    }
}
