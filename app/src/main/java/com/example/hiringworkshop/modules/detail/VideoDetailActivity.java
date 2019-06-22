package com.example.hiringworkshop.modules.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.uimodels.ChannelDetail;
import com.example.hiringworkshop.restApi.restApiModels.VideoComment;
import com.example.hiringworkshop.restApi.restApiModels.VideoDetail;
import com.example.hiringworkshop.modules.channel.ChannelActivity;
import com.example.hiringworkshop.mvp.ActivityView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VideoDetailActivity extends ActivityView<VideoDetailPresenter, DetailPresenterCallback> implements DetailPresenterCallback {

    private ImageView videoImageView;
    private TextView videoNameText;
    private TextView channelNameText;
    private ImageView likeBtn;
    private Button subscribeBtn;
    private ProgressBar progressBar;
    private RecyclerView commentsListview;
    private FloatingActionButton addCommentFabBtn;
    private VideoDetailRecyclerViewAdapter videoDetailRecyclerViewAdapter;
    private Dialog addCommentDialog;

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

        if (commentsListview != null) {
            commentsListview.setLayoutManager(new LinearLayoutManager(this));
        }

        getPresenter().getMovieDetail();
        getPresenter().getComments();

        if (addCommentFabBtn != null) {
            addCommentFabBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddCommentDialog();
                }
            });
        }
    }

    private void showAddCommentDialog() {
        if (addCommentDialog == null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add Comment");

            final EditText commentEditText = new EditText(this);
            commentEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
            builder.setView(commentEditText);

            builder.setPositiveButton("Comment", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String comment = commentEditText.getText().toString();
                    getPresenter().addComment(comment);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            addCommentDialog = builder.create();
        }

        addCommentDialog.show();
    }

    @Override
    protected DetailPresenterCallback getCallback() {

        //Used weakreference internally.
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
            videoNameText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChannelActivity.openChannelActivity(VideoDetailActivity.this);
                }
            });
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
                subscribeBtn.setText(getString(R.string.subscribed));
                subscribeBtn.setTextColor(ActivityCompat.getColor(this, android.R.color.white));
                subscribeBtn.setBackgroundColor(ActivityCompat.getColor(this, R.color.colorPrimary));
            } else {
                subscribeBtn.setText(getString(R.string.subscribe));
                subscribeBtn.setTextColor(ActivityCompat.getColor(this, android.R.color.white));
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
        if (commentsListview != null) {
            if (videoDetailRecyclerViewAdapter == null) {
                videoDetailRecyclerViewAdapter = new VideoDetailRecyclerViewAdapter(videoCommentList);
                commentsListview.setAdapter(videoDetailRecyclerViewAdapter);
            } else {
                videoDetailRecyclerViewAdapter.setAndUpdate(videoCommentList);
            }
        }
    }

    @Override
    public void showCommentsError() {

    }
}
