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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getIntent().getExtras();

        if (bundle != null && bundle.getParcelable(VIDEO_DATA) != null) {
            data = bundle.getParcelable(VIDEO_DATA);
            binding.setVideoData(data);
        }

        updateSubscription();


        binding.btSubscribe.setOnClickListener(this);
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

        updateSubscription();

        // Saved the subscription
        VideoDetails.getInstance().setSubscribe(binding.btSubscribe.getText().toString());

        // finish with success result code
        setResult(Activity.RESULT_OK);
        finish();
    }


    private void updateSubscription(){
        if (binding.btSubscribe.getText().toString().equals(getString(R.string.subscribed))){
            binding.btSubscribe.setText(getString(R.string.subscribe_me));
        }else {
            binding.btSubscribe.setText(getString(R.string.subscribed));
        }
    }
}
