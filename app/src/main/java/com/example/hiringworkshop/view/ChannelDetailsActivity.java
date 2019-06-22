package com.example.hiringworkshop.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;

import com.example.hiringworkshop.BR;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.databinding.ActivityChannelDetailsBinding;
import com.example.hiringworkshop.model.VideoDetails;
import com.example.hiringworkshop.model.response.VideoViewData;
import com.example.hiringworkshop.viewmodel.VideoViewModel;

import static com.example.hiringworkshop.model.Constants.BundleKKey.VIDEO_DATA;

public class ChannelDetailsActivity extends BaseActivity<ActivityChannelDetailsBinding, VideoViewModel> implements View.OnClickListener {

    Bundle bundle;
    VideoViewData data;
    AppCompatTextView mChannelDesc, mTvOwner;
    AppCompatButton mSubscribeMe;
    boolean isSubscribed =false, isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mChannelDesc = findViewById(R.id.tv_desc);
        mTvOwner = findViewById(R.id.tv_channel_owner);
        mSubscribeMe = findViewById(R.id.bt_subscribe);

        bundle = getIntent().getExtras();

        if (bundle != null && bundle.getParcelable(VIDEO_DATA) != null) {
            data = bundle.getParcelable(VIDEO_DATA);
            mChannelDesc.setText(data.getDescription());
            mTvOwner.setText(data.getChannelOwner());
        }

        if (VideoDetails.getInstance().isSubscribed){
            mSubscribeMe.setText("Subscribed");
        }else {
            mSubscribeMe.setText(getString(R.string.subscribe_me));
        }

        mSubscribeMe.setOnClickListener(this);

        // Updated the state
        isClicked = VideoDetails.getInstance().isSubscribed;
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_channel_details;
    }

    @Override
    VideoViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        return mViewModel;
    }

    @Override
    int getBindingVariable() {
        return BR.channel;
    }


    @Override
    public void onClick(View view) {

        if (!isClicked) {
            isClicked = true;
            mSubscribeMe.setText("Subscribed");
        } else {
            isClicked = false;
            mSubscribeMe.setText(getString(R.string.subscribe_me));

        }

        // Saved the subscribe state
        VideoDetails.getInstance().setSubscribed(isClicked);

        // finish with success result code
        setResult(Activity.RESULT_OK);
        finish();
    }
}
