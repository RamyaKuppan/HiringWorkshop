package com.example.hiringworkshop.modules.channel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.models.ChannelDetail;
import com.example.hiringworkshop.modules.detail.VideoDetailActivity;
import com.example.hiringworkshop.mvp.ActivityView;

import androidx.core.app.ActivityCompat;

public class ChannelActivity extends ActivityView<ChannelPresenter, ChannelPresenterCallback> implements ChannelPresenterCallback {

    private TextView channelNameText;
    private Button subscribeBtn;
    private ImageView videoImage;

    public static void openChannelActivity(Activity activity) {
        Intent intent = new Intent(activity, ChannelActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        channelNameText = findViewById(R.id.channel_name);
        subscribeBtn = findViewById(R.id.subscribe_btn);
        videoImage = findViewById(R.id.video_image);

        getPresenter().getMovieDetail();
    }

    @Override
    protected ChannelPresenterCallback getCallback() {
        return this;
    }

    @Override
    protected ChannelPresenter createPresenter() {
        return new ChannelPresenter();
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void showChannelDetail(final ChannelDetail channelDetail) {
        channelNameText.setText(channelDetail.getChannel());
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
        Glide.with(this).load(channelDetail.getVideoDetails()[0].getImage()).into(videoImage);
        videoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoDetailActivity.openVideoDetail(ChannelActivity.this);
            }
        });
    }

    @Override
    public void showError() {

    }
}
