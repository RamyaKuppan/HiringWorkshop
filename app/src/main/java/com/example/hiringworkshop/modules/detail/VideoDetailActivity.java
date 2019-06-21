package com.example.hiringworkshop.modules.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.models.ChannelDetail;
import com.example.hiringworkshop.models.VideoComment;
import com.example.hiringworkshop.models.VideoDetail;
import com.example.hiringworkshop.modules.channel.ChannelActivity;
import com.example.hiringworkshop.mvp.ActivityView;

import java.util.List;

public class VideoDetailActivity extends ActivityView<VideoDetailPresenter, DetailPresenterCallback> implements DetailPresenterCallback {

    private ImageView videoImageView;
    private TextView videoNameText;
    private TextView channelNameText;
    private ImageView likeBtn;
    private Button subscribeBtn;
    private ProgressBar progressBar;
    private RecyclerView commentsListview;
    private FloatingActionButton addCommentFabBtn;

    public static void openVideoDetail(Activity activity) {
        Intent intent = new Intent(activity, VideoDetailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoImageView = findViewById(R.id.video_image);
        videoNameText = findViewById(R.id.video_name);
        channelNameText = findViewById(R.id.channel_name);
        likeBtn = findViewById(R.id.like_btn);
        subscribeBtn = findViewById(R.id.channel_btn);
        progressBar = findViewById(R.id.progress_bar);
        commentsListview = findViewById(R.id.comments_list_view);
        addCommentFabBtn = findViewById(R.id.add_comment);

        getPresenter().getMovieDetail();
    }

    @Override
    protected DetailPresenterCallback getCallback() {
        return this;
    }

    @Override
    protected VideoDetailPresenter createPresenter() {
        return new VideoDetailPresenter();
    }

    @Override
    public void showVideoLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMovieDetail(final VideoDetail videoDetail) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        if (videoNameText != null) {
            videoNameText.setText(videoDetail.getDescription());
        }
        Glide.with(this).load(videoDetail.getImage()).into(videoImageView);
        if (likeBtn != null) {
            if (videoDetail.isLiked()) {
                likeBtn.setImageResource(R.drawable.ic_favorite_black_24dp);
            } else {
                likeBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }

            likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPresenter().toggleLike();
                }
            });
        }


    }

    @Override
    public void showVideoFetchingError() {

    }

    @Override
    public void showChannelInfo(ChannelDetail channelDetail) {
        if (subscribeBtn != null) {
            if (channelDetail.isSubscribed()) {
                subscribeBtn.setText("Subscribed!");
                subscribeBtn.setBackgroundColor(ActivityCompat.getColor(this, R.color.colorPrimary));
            } else {
                subscribeBtn.setText("Subscribe");
                subscribeBtn.setBackgroundColor(ActivityCompat.getColor(this, R.color.colorAccent));
            }

            subscribeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPresenter().toggleSubscribe();
                }
            });
        }

        if (channelNameText != null) {
            channelNameText.setText(channelDetail.getChannel());
            channelNameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChannelActivity.openChannelActivity(VideoDetailActivity.this);
                }
            });
        }
    }

    @Override
    public void showLoaderOnCommentsSection() {

    }

    @Override
    public void showComments(List<VideoComment> videoCommentList) {

    }

    @Override
    public void showCommentsError() {

    }
}
