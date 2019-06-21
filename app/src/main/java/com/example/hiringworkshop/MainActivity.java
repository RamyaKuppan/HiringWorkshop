package com.example.hiringworkshop;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hiringworkshop.Models.CommentData;
import com.example.hiringworkshop.Models.VideoResponse;
import com.example.hiringworkshop.adapter.CommentAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {
    public final static String VIDEO_URL = "https://hiringworkshop.herokuapp.com/api/workshop/video";
    public final static String COMMENTS_URL = "https://hiringworkshop.herokuapp.com/api/workshop/comments";
    TextView mVideoTitleTv;
    TextView mVideoDescTv;
    TextView mVideoChannelLinkTv;
    TextView mVideoSubscribeTv;
    RecyclerView mLoadCommentRV;

    Button mVideoLikeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoTitleTv = findViewById(R.id.textView_video_title);
        mVideoDescTv = findViewById(R.id.textView_video_desc);
        mVideoLikeButton = findViewById(R.id.button_like);
        mVideoChannelLinkTv = findViewById(R.id.textview_channel_link);
        mVideoSubscribeTv = findViewById(R.id.textView_subscribe);
        mLoadCommentRV = findViewById(R.id.recylerview_load_comment);
        mVideoChannelLinkTv.setOnClickListener(v -> {
            startActivity(new Intent(this, ChannelActivity.class));
            finish();
        });

        new GetVideoData().execute();
        new GetCommentsList().execute();
        MainActivityViewModel model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        if (model.getLiked()) {
            mVideoLikeButton.setText("Liked");
        } else {
            mVideoLikeButton.setText("Like");
        }
        mVideoLikeButton.setOnClickListener(v -> {
            if (model.getLiked()) {
                model.setLiked(false);
                mVideoLikeButton.setText("Like");
            } else {
                model.setLiked(true);
                mVideoLikeButton.setText("Liked");
            }

        });
    }

    public class GetVideoData extends AsyncTask<String, String, String> {
        String videoData;

        @Override
        protected String doInBackground(String... strings) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Request request = new Request.Builder()
                    .url(VIDEO_URL)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                videoData = response.body() != null ? response.body().string() : null;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return videoData;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            VideoResponse videoResponse = gson.fromJson(videoData, VideoResponse.class);
            mVideoTitleTv.setText(videoResponse.getChannel());
            mVideoDescTv.setText(videoResponse.getDescription());
            ImageView imageView = findViewById(R.id.show_video_imageview);
            Glide.with(MainActivity.this).load(videoResponse.getImage()).into(imageView);
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("VideoSubscribeData", s);
            editor.commit();
        }
    }

    public class GetCommentsList extends AsyncTask<String, String, String> {
        String apiResponse;

        @Override
        protected String doInBackground(String... strings) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Request request = new Request.Builder()
                    .url(COMMENTS_URL)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                apiResponse = response.body() != null ? response.body().string() : null;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return apiResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Type type = new TypeToken<List<CommentData>>() {
            }.getType();
            Gson gson = new Gson();
            List<CommentData> commentDataList = gson.fromJson(s, type);

            CommentAdapter mAdapter = new CommentAdapter(commentDataList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mLoadCommentRV.setLayoutManager(mLayoutManager);
            mLoadCommentRV.setItemAnimator(new DefaultItemAnimator());
            mLoadCommentRV.setAdapter(mAdapter);
        }
    }

}
