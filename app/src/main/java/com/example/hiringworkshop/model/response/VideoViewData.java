package com.example.hiringworkshop.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoViewData implements Parcelable {
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


    protected VideoViewData(Parcel in) {
        image = in.readString();
        description = in.readString();
        channel = in.readString();
        uploader = in.readString();
        views = in.readString();
        uploadedTimeline = in.readString();
        channelSubscribers = in.readByte() == 0x00 ? null : in.readInt();
        channelOwner = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(description);
        dest.writeString(channel);
        dest.writeString(uploader);
        dest.writeString(views);
        dest.writeString(uploadedTimeline);
        if (channelSubscribers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(channelSubscribers);
        }
        dest.writeString(channelOwner);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VideoViewData> CREATOR = new Parcelable.Creator<VideoViewData>() {
        @Override
        public VideoViewData createFromParcel(Parcel in) {
            return new VideoViewData(in);
        }

        @Override
        public VideoViewData[] newArray(int size) {
            return new VideoViewData[size];
        }
    };


    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(AppCompatImageView view, String url) {
        Glide.with(view).load(url).into(view);
    }

}