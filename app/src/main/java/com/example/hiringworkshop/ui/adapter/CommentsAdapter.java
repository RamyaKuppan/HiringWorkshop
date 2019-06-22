package com.example.hiringworkshop.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiringworkshop.R;
import com.example.hiringworkshop.data.CommentDetail;

import java.util.ArrayList;
import java.util.List;


public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    private List<CommentDetail> dataList = new ArrayList<>();

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_comment_item, parent, false);
        return new CommentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentsViewHolder holder, final int position) {
        final CommentDetail data = dataList.get(position);
        holder.textView.setText(data.getComment());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class CommentsViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        CommentsViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.text_comment);
        }
    }

    public void setData(List<CommentDetail> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }
}
