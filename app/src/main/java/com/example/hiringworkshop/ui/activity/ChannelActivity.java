package com.example.hiringworkshop.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hiringworkshop.AppConstants;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.data.VideoDetails;

public class ChannelActivity extends AppCompatActivity {
    private TextView channelTitle;
    private TextView channeDescription;
    private TextView channesubscriptionsCount;
    private Button subscribe;
    private VideoDetails video;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        channelTitle = findViewById(R.id.channel_title);
        channeDescription = findViewById(R.id.channel_description);
        channesubscriptionsCount = findViewById(R.id.channel_subscriptions);
        subscribe = findViewById(R.id.button_subscribe);
        restoreIntent();
        setUpViews();
    }

    private void restoreIntent() {
        if (getIntent().hasExtra(AppConstants.KEY_VIDEO)) {
            video = getIntent().getParcelableExtra(AppConstants.KEY_VIDEO);
        }
    }

    private void setUpViews() {
        if (video != null) {
            channelTitle.setText(video.getChannel());
            channeDescription.setText(video.getDescription());
            channesubscriptionsCount.setText(video.getChannelSubscribers());
        }
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void updateSubscriptionButton() {

    }
}
