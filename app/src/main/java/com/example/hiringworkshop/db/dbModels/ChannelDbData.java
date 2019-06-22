package com.example.hiringworkshop.db.dbModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "channel")
public class ChannelDbData {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
