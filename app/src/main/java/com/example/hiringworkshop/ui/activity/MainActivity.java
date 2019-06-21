package com.example.hiringworkshop.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiringworkshop.AppConstants;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.Utils;
import com.example.hiringworkshop.api.VideoLoaderCallbacks;
import com.example.hiringworkshop.data.CommentDetail;
import com.example.hiringworkshop.data.VideoDetails;
import com.example.hiringworkshop.loaders.VideoLoader;
import com.example.hiringworkshop.ui.adapter.CommentsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements VideoLoaderCallbacks {

    private ImageView videoImage;
    private TextView videoTitle;
    private TextView videoDescription;
    private ImageView videoLike;
    private RecyclerView commentsRecycler;
    private ImageButton buttonPostComment;
    private EditText editTextComment;
    private VideoLoader loader;
    private boolean isVideoLiked = false;
    private VideoDetails video;
    private CommentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoImage = findViewById(R.id.image_video);
        videoTitle = findViewById(R.id.text_title);
        videoDescription = findViewById(R.id.text_description);
        videoLike = findViewById(R.id.button_like);
        commentsRecycler = findViewById(R.id.recycler_view_comments);
        buttonPostComment = findViewById(R.id.button_post_comment);
        editTextComment = findViewById(R.id.edit_text_comment);
        loader = new VideoLoader(this);
        loader.loadVideo();
        setUpViews();
    }

    private void setUpViews() {
        updateLikeButtonState();
        videoLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVideoLiked = !isVideoLiked;
                updateLikeButtonState();
            }
        });
        videoTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChannel();
            }
        });
        buttonPostComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postComments();
            }
        });
        commentsRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentsAdapter();
        commentsRecycler.setAdapter(adapter);
    }

    private void postComments() {
        loader.postComment(editTextComment.getText().toString());
    }

    private void updateLikeButtonState() {
        videoLike.setImageResource(isVideoLiked ? R.drawable.ic_liked_24 : R.drawable.ic_like_24);
    }

    private void goToChannel() {
        Intent intent = new Intent(this, ChannelActivity.class);
        intent.putExtra(AppConstants.KEY_VIDEO, video);
        startActivity(intent);
    }

    @Override
    public void onVideoDetailLoaded(VideoDetails data) {
        Utils.loadImage(videoImage, data.getImage());
        videoTitle.setText(data.getChannel());
        videoDescription.setText(data.getDescription());
        loader.getComments();
    }

    @Override
    public void onCommentsLoaded(List<CommentDetail> data) {
        adapter.setData(data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(AppConstants.KEY_LIKED, isVideoLiked);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        isVideoLiked = savedInstanceState.getBoolean(AppConstants.KEY_LIKED);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
