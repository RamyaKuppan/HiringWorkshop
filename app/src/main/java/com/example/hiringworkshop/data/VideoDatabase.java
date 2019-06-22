package com.example.hiringworkshop.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hiringworkshop.VideoApplication;
import com.example.hiringworkshop.model.Channel;
import com.example.hiringworkshop.model.Comment;
import com.example.hiringworkshop.model.Details;
import com.example.hiringworkshop.model.User;

@Database(entities = {
        Channel.class, Comment.class, Details.class, User.class}, version = 1, exportSchema = false)
public abstract class VideoDatabase extends RoomDatabase {

    private static VideoDatabase instance;

    public static VideoDatabase getInstance(){
        if(instance == null){
            instance = Room.databaseBuilder(VideoApplication.getContext(),
                    VideoDatabase.class, "video_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
