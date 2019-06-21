package com.example.hiringworkshop.modules.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class VideoDetailRecyclerViewAdapter extends RecyclerView.Adapter<CommentsViewHolder> {

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder commentsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}


class CommentsViewHolder extends RecyclerView.ViewHolder {

    public CommentsViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
