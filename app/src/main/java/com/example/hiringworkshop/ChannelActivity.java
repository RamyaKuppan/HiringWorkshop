package com.example.hiringworkshop;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hiringworkshop.Models.VideoResponse;
import com.google.gson.Gson;

public class ChannelActivity extends AppCompatActivity {
    TextView channelOwnerTv;
    TextView channelNameTv;
    TextView channelSubscribeTv;
    Button subscribeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String Data = pref.getString("VideoSubscribeData", null);
        Gson gson = new Gson();
        VideoResponse videoResponse = gson.fromJson(Data, VideoResponse.class);
        channelNameTv.setText(videoResponse.getChannel());
        channelOwnerTv.setText(videoResponse.getChannelOwner());
        channelSubscribeTv.setText(videoResponse.getChannelSubscribers());
        subscribeButton.setOnClickListener(v -> {
            if (videoResponse.getIschannelSubscribed().contains("Subscribed")) {
                videoResponse.setIschannelSubscribed("Subscribe");
            }
            else{
                videoResponse.setIschannelSubscribed("Subscribed");
            };
        });

    }
}
