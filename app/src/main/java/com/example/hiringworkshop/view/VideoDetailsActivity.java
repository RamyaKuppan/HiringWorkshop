package com.example.hiringworkshop.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.model.Constants;
import com.example.hiringworkshop.model.IWebserviceListener;
import com.example.hiringworkshop.model.VideoDetails;
import com.example.hiringworkshop.model.VideoViewData;
import com.example.hiringworkshop.model.WebserviceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.hiringworkshop.model.Constants.BundleKKey.OPINION;

public class VideoDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatTextView mTvChannel;
    AppCompatTextView mTvViews;
    AppCompatTextView mTvDesc;
    AppCompatTextView mTvClickHere;
    AppCompatImageView mIvVideo;
    AppCompatButton mBtLike;
    AppCompatButton mBtSubscribe;

    Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find ALl Views
        mTvChannel = findViewById(R.id.tv_channel);
        mTvViews = findViewById(R.id.tv_views);
        mTvDesc = findViewById(R.id.tv_desc);
        mIvVideo = findViewById(R.id.iv_video);
        mBtLike = findViewById(R.id.bt_like);
        mTvClickHere = findViewById(R.id.tv_click_here);
        mBtSubscribe = findViewById(R.id.bt_subscribe);

        mBtLike.setOnClickListener(this);
        mTvClickHere.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Create instance for Volley which created for every call
        WebserviceManager manager = new WebserviceManager(iVideoListener);

        // Initiating the API CALL
        getRequestQueue().add(manager);
    }

    public RequestQueue getRequestQueue() {
        return Volley.newRequestQueue(this);
    }


    /**
     * Webservice Callbacks
     */
    private IWebserviceListener iVideoListener = new IWebserviceListener() {
        @Override
        public void onSuccessfulApi(String response) {

            // Find Response Type and Convert String Response to Respective JSON Object model
            Type responseType = new TypeToken<VideoViewData>() {
            }.getType();
            VideoViewData data = new Gson().fromJson(response, responseType);

            mBundle = new Bundle();
            mBundle.putParcelable(Constants.BundleKKey.VIDEO_DATA, data);

            // Binding UI values
            Glide.with(VideoDetailsActivity.this).load(data.getImage()).into(mIvVideo);
            mTvChannel.setText(data.getChannel());
            mTvViews.setText(data.getViews());
            mTvDesc.setText(data.getDescription());
        }

        @Override
        public void onFailureApi(String error) {
            Log.e("VideoDetailsActivity", "Error is : " + error);
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_like:
                if (mBtLike.getText().equals("Like")) {
                    mBtLike.setText("Liked");

                } else if (mBtLike.getText().equals("Liked")) {
                    mBtLike.setText("Like");

                }
                break;
            case R.id.tv_click_here:
                Intent intent = new Intent(this, ChannelDetailsActivity.class);
                if (mBundle != null) {
                    intent.putExtras(mBundle);
                }
                startActivityForResult(intent, Constants.RequestCodes.SUBSCRIBE_REQUEST);
                break;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Saving the state before destroying the activity instance
        outState.putString(OPINION, mBtLike.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Getting the state from the saved bundle
        mBtLike.setText(savedInstanceState.getString(OPINION));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RequestCodes.SUBSCRIBE_REQUEST && resultCode == Activity.RESULT_OK) {

            if (VideoDetails.getInstance().isSubscribed) {
                mBtSubscribe.setText("Subscribed");
            } else {
                mBtSubscribe.setText(getString(R.string.not_subscribe));
            }
        }

    }
}
