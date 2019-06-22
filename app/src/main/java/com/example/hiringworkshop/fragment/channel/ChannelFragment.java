package com.example.hiringworkshop.fragment.channel;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiringworkshop.R;
import com.example.hiringworkshop.activity.MainActivity;
import com.example.hiringworkshop.databinding.FragmentChannelBinding;


public class ChannelFragment extends Fragment {

    public static final String TAG = ChannelFragment.class.getName();

    public static ChannelFragment newInstance() {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentChannelBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_channel, container, false);
        binding.setViewModel(((MainActivity)getActivity()).getViewModel());
        return binding.getRoot();
    }

}
