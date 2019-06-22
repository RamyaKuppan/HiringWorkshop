package com.example.hiringworkshop.repositories;

import com.example.hiringworkshop.models.ChannelDetail;
import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;

import java.util.HashSet;
import java.util.Set;

public class MockRest {

    private static final MockRest instance = new MockRest();

    public static MockRest getInstance() {
        return instance;
    }

    private final Set<VideoDetail> likedVideos = new HashSet<>();

    private final Set<ChannelDetail> subscribedChannels = new HashSet<>();

    public void likeVideo(VideoDetail videoDetail) {
        likedVideos.add(videoDetail);
    }

    public void removeVideoAsLiked(VideoDetail videoDetail) {
        likedVideos.remove(videoDetail);
    }

    public void subscribeChannel(ChannelDetail channelDetail) {
        subscribedChannels.add(channelDetail);
    }

    public void removeVideoAsLiked(ChannelDetail channelDetail) {
        subscribedChannels.remove(channelDetail);
    }

    public Set<VideoDetail> getLikedVideos() {
        return likedVideos;
    }

    public Set<ChannelDetail> getSubscribedChannels() {
        return subscribedChannels;
    }
}
