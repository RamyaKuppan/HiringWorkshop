package com.example.hiringworkshop;

import android.widget.ImageView;

public class Utils {

    public static void loadImage(ImageView view, String url) {
        GlideApp.with(view.getContext())
                .load(url)
                .into(view);
    }
}
