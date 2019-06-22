package com.example.hiringworkshop.DatabaseRepo;

import android.content.Context;

import com.example.hiringworkshop.models.VideoModel;

import io.realm.Realm;

public class VideoTable {

    /**
     * Realm object
     */
    private Realm realm;

    /**
     * Constructor
     *
     * @param context context
     */
    public VideoTable(Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    /**
     * Method to write data in video list table
     *
     * @param videoModel video data
     */
    public void writeVideo(VideoModel videoModel) {
        realm.executeTransaction(mRealm -> {

            VideoModel video = new VideoModel();
                video.setId(videoModel.getId());
                video.setChannel(videoModel.getChannel());
                video.setDescription(video.getDescription());
                video.setImage(video.getImage());
                video.setUploader(video.getUploader());

            mRealm.insertOrUpdate(video);
        });
    }

}
