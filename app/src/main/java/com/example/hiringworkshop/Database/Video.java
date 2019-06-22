package com.example.hiringworkshop.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Channel.class,
        parentColumns = "ChannelID",
        childColumns = "videoId"
))
public class Video {
    @PrimaryKey(autoGenerate = true)
    private int videoId;

    private String image;

    private String description;

    private String uploader;

    private String views;

    private String uploadedTimeline;

}
