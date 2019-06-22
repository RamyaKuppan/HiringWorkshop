package com.example.hiringworkshop.db;

import com.example.hiringworkshop.db.dbModels.LikedVideosDbData;
import com.example.hiringworkshop.db.dbModels.VideoDbData;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {VideoDbData.class, LikedVideosDbData.class}, version = 1, exportSchema = false)
public abstract class VideoAppDatabase extends RoomDatabase {
    public abstract VideoDao getVideoDao();
}
