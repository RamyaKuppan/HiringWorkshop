package com.example.hiringworkshop.view;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

public abstract class BaseActivity<DB extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {

    protected DB binding;
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, getLayoutId());

        /**
         *  Common Place for Activity's View Model Object Creation (Registeration)
         * */

        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        binding.setLifecycleOwner(this);

        /**
         *  One Binding Variable for Each XML Layout
         * */

        binding.setVariable(getBindingVariable(), mViewModel);
        binding.executePendingBindings();
    }


    abstract int getLayoutId();

    abstract VM getViewModel();

    abstract int getBindingVariable();

}
