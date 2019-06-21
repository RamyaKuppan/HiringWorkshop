package com.example.hiringworkshop.api;

import com.example.hiringworkshop.data.CommentDetail;
import com.example.hiringworkshop.data.VideoDetails;

import java.util.List;

public interface VideoLoaderCallbacks {
    void onVideoDetailLoaded(VideoDetails data);

    void onCommentsLoaded(List<CommentDetail> data);
}
