package com.example.hiringworkshop.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoDetails implements Parcelable {

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
    public final static Parcelable.Creator<VideoDetails> CREATOR = new Creator<VideoDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideoDetails createFromParcel(Parcel in) {
            return new VideoDetails(in);
        }

        public VideoDetails[] newArray(int size) {
            return (new VideoDetails[size]);
        }

    };

    protected VideoDetails(Parcel in) {
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.channel = ((String) in.readValue((String.class.getClassLoader())));
        this.uploader = ((String) in.readValue((String.class.getClassLoader())));
        this.views = ((String) in.readValue((String.class.getClassLoader())));
        this.uploadedTimeline = ((String) in.readValue((String.class.getClassLoader())));
        this.channelSubscribers = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.channelOwner = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VideoDetails() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(image);
        dest.writeValue(description);
        dest.writeValue(channel);
        dest.writeValue(uploader);
        dest.writeValue(views);
        dest.writeValue(uploadedTimeline);
        dest.writeValue(channelSubscribers);
        dest.writeValue(channelOwner);
    }

    public int describeContents() {
        return 0;
    }

}

