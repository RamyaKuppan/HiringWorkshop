package com.example.hiringworkshop;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hiringworkshop.Models.VideoResponse;
import com.google.gson.Gson;

public class ChannelActivity extends AppCompatActivity {
    TextView channelOwnerTv;
    TextView channelNameTv;
    TextView channelSubscribeTv;
    Button subscribeButton;
    ImageView imageviewVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        imageviewVideo = findViewById(R.id.imageView);
        channelNameTv = findViewById(R.id.textView_channel_name);
        channelOwnerTv = findViewById(R.id.textView_channel_owner);
        channelSubscribeTv = findViewById(R.id.textView_channel_subscribe);
        subscribeButton = findViewById(R.id.button_subsribe);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String Data = pref.getString("VideoSubscribeData", null);
        Gson gson = new Gson();
        VideoResponse videoResponse = gson.fromJson(Data, VideoResponse.class);
        Glide.with(ChannelActivity.this).load(videoResponse.getImage()).into(imageviewVideo);
        channelNameTv.setText(videoResponse.getChannel());
        channelOwnerTv.setText(videoResponse.getChannelOwner());
        channelSubscribeTv.setText(videoResponse.getChannelSubscribers().toString());
        subscribeButton.setOnClickListener(v -> {
            if (videoResponse.getIschannelSubscribed().contains("Subscribed")) {
                videoResponse.setIschannelSubscribed("Subscribe");
            } else {
                videoResponse.setIschannelSubscribed("Subscribed");
            }
            ;
        });

    }
}
