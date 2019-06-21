package com.example.hiringworkshop.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.hiringworkshop.R;
import com.example.hiringworkshop.ViewModelCommunicator;
import com.example.hiringworkshop.databinding.ActivityMainBinding;
import com.example.hiringworkshop.fragment.ChannelFragment;
import com.example.hiringworkshop.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity implements ViewModelCommunicator {

    private FragmentManager mFragmentManager;

    public MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
        viewModel.setCommunicator(this);
        viewModel.getData();
    }

    @Override
    public void loadVideoFragment() {
        mFragmentManager.beginTransaction()
                .replace(R.id.container, VideoFragment.newInstance(), VideoFragment.TAG)
                .commit();
    }

    @Override
    public void loadChannelFragment() {
        mFragmentManager.beginTransaction()
                .replace(R.id.container, ChannelFragment.newInstance(), ChannelFragment.TAG)
                .addToBackStack(ChannelFragment.TAG)
                .commit();
    }

    public MainActivityViewModel getViewModel() {
        return viewModel;
    }
}
