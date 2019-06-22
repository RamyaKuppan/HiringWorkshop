package com.example.hiringworkshop;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class BindingUtils {

    @BindingAdapter({"bind:imageUrl"})
    public static void showThumbnail(ImageView imageView, String imageUrl) {
        if(imageUrl != null) {
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
        }
    }

    @BindingAdapter({"bind:setAdapter"})
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

}
