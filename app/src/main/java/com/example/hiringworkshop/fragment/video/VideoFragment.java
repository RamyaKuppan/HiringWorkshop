package com.example.hiringworkshop.fragment.video;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiringworkshop.activity.MainActivity;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.databinding.FragmentVideoBinding;
import com.example.hiringworkshop.model.Comment;

import java.util.List;


public class VideoFragment extends Fragment {

    public static final String TAG = VideoFragment.class.getName();

    private VideoFragmentViewModel mViewModel;

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewModel = ViewModelProviders.of(this).get(VideoFragmentViewModel.class);
        FragmentVideoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        if(savedInstanceState == null){
            mViewModel.getCommentsLiveData().observe(this, new Observer<List<Comment>>() {
                @Override
                public void onChanged(List<Comment> comments) {
                    mViewModel.notifyCommentsAdapter(comments);
                }
            });
        }
        binding.setViewModel(mViewModel);
        binding.setActivityViewModel(((MainActivity)getActivity()).getViewModel());
        return binding.getRoot();
    }

}
