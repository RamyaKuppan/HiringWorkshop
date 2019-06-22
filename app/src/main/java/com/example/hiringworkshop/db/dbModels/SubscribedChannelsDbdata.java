package com.example.hiringworkshop.db.dbModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subscribed_channels")
public class SubscribedChannelsDbdata {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "channel_id")
    private long channelId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}
