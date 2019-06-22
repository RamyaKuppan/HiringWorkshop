package com.example.hiringworkshop.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentDetail implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    public final static Parcelable.Creator<CommentDetail> CREATOR = new Creator<CommentDetail>() {

        @SuppressWarnings({
                "unchecked"
        })
        public CommentDetail createFromParcel(Parcel in) {
            return new CommentDetail(in);
        }

        public CommentDetail[] newArray(int size) {
            return (new CommentDetail[size]);
        }

    };

    protected CommentDetail(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.comment = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.timestamp = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public CommentDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return name;
    }

    public void setUser(String user) {
        this.name = user;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(comment);
        dest.writeValue(name);
        dest.writeValue(timestamp);
    }

    public int describeContents() {
        return 0;
    }

}
