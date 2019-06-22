package com.example.hiringworkshop.Database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comments {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("timestamp")
    @Expose
    private long timestamp;

}
