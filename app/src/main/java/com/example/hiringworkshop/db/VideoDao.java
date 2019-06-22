package com.example.hiringworkshop.db;

import com.example.hiringworkshop.db.dbModels.LikedVideosDbData;
import com.example.hiringworkshop.db.dbModels.VideoAndLikedData;
import com.example.hiringworkshop.db.dbModels.VideoDbData;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface VideoDao {

    @Query("SELECT video.*, liked_videos.* from video LEFT JOIN liked_videos ON video.vid = liked_videos.video_id")
    List<VideoAndLikedData> getVideoDataList();

    @Insert(onConflict = REPLACE)
    long insertVideo(VideoDbData videoDbData);

    @Insert(onConflict = REPLACE)
    long insertLikedVideo(LikedVideosDbData likedVideos);
}
