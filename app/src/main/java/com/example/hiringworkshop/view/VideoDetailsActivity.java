package com.example.hiringworkshop.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hiringworkshop.BR;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.adapter.CommentsAdapter;
import com.example.hiringworkshop.databinding.ActivityVideoDetailsBinding;
import com.example.hiringworkshop.model.Constants;
import com.example.hiringworkshop.model.VideoDetails;
import com.example.hiringworkshop.model.response.CommentsData;
import com.example.hiringworkshop.viewmodel.VideoViewModel;

import java.util.List;

public class VideoDetailsActivity extends BaseActivity<ActivityVideoDetailsBinding, VideoViewModel> {

    CommentsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setUpdateLike(getString(R.string.like));

        // Send Video Details Request
        mViewModel.sendVideoDetailsRequest();

        // Send Request to get Comments
        mViewModel.sendGetCommentsRequest();


        // Observer latest video data to views
        mViewModel.getVideoViewData().observe(this, data -> binding.setVideoData(data));


        binding.tvClickHere.setOnClickListener(view -> {
            Intent intent = new Intent(VideoDetailsActivity.this, ChannelDetailsActivity.class);
            startActivity(intent);
        });

        // Updated the state of like an liked
        binding.btLike.setOnClickListener(view -> {
            if (binding.btLike.getText().toString().equals(getString(R.string.like))) {
                binding.setUpdateLike(getString(R.string.liked));
            } else {
                binding.setUpdateLike(getString(R.string.like));
            }
        });

        // Update Recyclerview with comments
        mViewModel.getCommentsData().observe(this, commentsData -> {
            mAdapter = new CommentsAdapter(commentsData, null);
            binding.rvCommentsList.setAdapter(mAdapter);

        });
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_video_details;
    }

    @Override
    VideoViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        return mViewModel;
    }

    @Override
    int getBindingVariable() {
        return BR.video;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Getting the state from the saved bundle
        binding.setUpdateLike(binding.getUpdateLike());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RequestCodes.SUBSCRIBE_REQUEST && resultCode == Activity.RESULT_OK) {

            binding.btSubscription.setText(VideoDetails.getInstance().getSubscribe());
        }

    }

}
