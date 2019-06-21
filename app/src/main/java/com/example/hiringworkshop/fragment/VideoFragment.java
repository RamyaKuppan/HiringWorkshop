package com.example.hiringworkshop.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiringworkshop.activity.MainActivity;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.databinding.FragmentVideoBinding;


public class VideoFragment extends Fragment {

    public static final String TAG = VideoFragment.class.getName();

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentVideoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        binding.setViewModel(((MainActivity)getActivity()).getViewModel());
        return binding.getRoot();
    }

}
