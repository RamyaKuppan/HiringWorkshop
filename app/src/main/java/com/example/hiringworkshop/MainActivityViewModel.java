package com.example.hiringworkshop;

import android.arch.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {


    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    private Boolean isLiked = false;

}
