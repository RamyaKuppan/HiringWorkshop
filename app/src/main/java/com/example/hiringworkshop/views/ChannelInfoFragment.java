package com.example.hiringworkshop.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hiringworkshop.R;
import com.example.hiringworkshop.models.VideoModel;
import com.example.hiringworkshop.viewmodel.ChannelInfoViewModel;

public class ChannelInfoFragment extends Fragment {

    private ChannelInfoViewModel mViewModel;

    private static VideoModel videoModel;

    private TextView tvChannelName;


    private TextView tvChannnelDesc;

    private TextView tvVideolink;


    public static ChannelInfoFragment newInstance(VideoModel mVideoModel) {
        videoModel = mVideoModel;
        return new ChannelInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.channel_info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChannelInfoViewModel.class);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseViews(view);
    }

    private void initialiseViews(View view) {
        tvChannelName = view.findViewById(R.id.tv_channel_name);
        tvChannnelDesc = view.findViewById(R.id.tv_channel_desc);
        tvVideolink = view.findViewById(R.id.tv_videoLink);
        fillInDataToUI();
    }

    private void fillInDataToUI() {
        tvChannelName.setText(videoModel.getChannel());
        tvChannnelDesc.setText(videoModel.getDescription());
        tvVideolink.setText(videoModel.getViews());
    }
}
