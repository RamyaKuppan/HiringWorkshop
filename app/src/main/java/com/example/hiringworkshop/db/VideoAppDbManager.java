package com.example.hiringworkshop.db;

import com.example.hiringworkshop.VideoApp;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class VideoAppDbManager {

    private static final VideoAppDbManager instance = new VideoAppDbManager();

    private VideoAppDatabase database;

    public static VideoAppDbManager getInstance() {
        return instance;
    }

    private VideoAppDbManager() {
        database = Room.databaseBuilder(VideoApp.getAppContext(), VideoAppDatabase.class, "videoapp.db").build();
    }

    public VideoAppDatabase getDatabase() {
        return database;
    }
}
