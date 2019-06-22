package com.example.hiringworkshop.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "UserID",
        childColumns = "ChannelID"
))
public class Channel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long ChannelID;
    private Integer channelSubscribers;
    private String channelOwner;

}
