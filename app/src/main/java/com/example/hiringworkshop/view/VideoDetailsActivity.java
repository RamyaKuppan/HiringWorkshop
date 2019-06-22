package com.example.hiringworkshop.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hiringworkshop.BR;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.adapter.CommentsAdapter;
import com.example.hiringworkshop.databinding.ActivityVideoDetailsBinding;
import com.example.hiringworkshop.model.Constants;
import com.example.hiringworkshop.model.response.CommentsData;
import com.example.hiringworkshop.model.VideoDetails;
import com.example.hiringworkshop.viewmodel.VideoViewModel;

import java.util.ArrayList;
import java.util.List;

public class VideoDetailsActivity extends BaseActivity<ActivityVideoDetailsBinding, VideoViewModel> {

    CommentsAdapter mAdaprer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setUpdateLike(getString(R.string.like));

        mViewModel.sendVideoDetailsRequest();
        mViewModel.sendGetCommentsRequest();

        mViewModel.getVideoViewData().observe(this, data -> binding.setVideoData(data));

        mViewModel.getBundleVideoData().observe(this, mBundle -> {
            Intent intent = new Intent(VideoDetailsActivity.this, ChannelDetailsActivity.class);
            if (mBundle != null) {
                intent.putExtras(mBundle);
            }
            startActivityForResult(intent, Constants.RequestCodes.SUBSCRIBE_REQUEST);
        });

        binding.btLike.setOnClickListener(view -> {
            if (binding.btLike.getText().toString().equals(getString(R.string.like))) {
                binding.setUpdateLike(getString(R.string.liked));
            } else {
                binding.setUpdateLike(getString(R.string.like));
            }
        });

        mViewModel.getCommentsData().observe(this, new Observer<List<CommentsData>>() {
            @Override
            public void onChanged(List<CommentsData> commentsData) {

                mAdaprer = new CommentsAdapter(commentsData,null);
                binding.rvCommentsList.setAdapter(mAdaprer);
            }
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

            if (VideoDetails.getInstance().isSubscribed) {
                binding.btSubscription.setText(getString(R.string.subscribed));
            } else {
                binding.btSubscription.setText(getString(R.string.not_subscribe));
            }
        }

    }
}
