package com.example.hiringworkshop.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Details {
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_UPLOADER = "uploader";
    public static final String COLUMN_VIEWS = "views";
    public static final String COLUMN_UPLOADED_TIMELINE = "uploaded_timeline";
    public static final String COLUMN_IS_LIKED = "isLiked";

    @ColumnInfo(name = COLUMN_IMAGE)
    @SerializedName("image")
    String image;

    @ColumnInfo(name = COLUMN_DESCRIPTION)
    @SerializedName("description")
    String description;

    @ColumnInfo(name = COLUMN_UPLOADER)
    @SerializedName("uploader")
    String uploader;

    @ColumnInfo(name = COLUMN_VIEWS)
    @SerializedName("views")
    String views;

    @ColumnInfo(name = COLUMN_UPLOADED_TIMELINE)
    @SerializedName("uploadedTimeline")
    String uploadedTimeline;

    @ColumnInfo(name = COLUMN_IS_LIKED)
    int isLiked;

//    Channel channelInfo;


    public Details(String image, String description, String uploader, String views, String uploadedTimeline, int isLiked) {
        this.image = image;
        this.description = description;
        this.uploader = uploader;
        this.views = views;
        this.uploadedTimeline = uploadedTimeline;
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked == 1;
    }

    public void setLiked(boolean liked) {
        isLiked = liked ? 1 : 0;
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
        // todo.. get from channel, detailsId..
//        return channelInfo;
        return null;
    }

    public void setChannelInfo(Channel channelInfo) {
//        this.channelInfo = channelInfo;
    }
}
