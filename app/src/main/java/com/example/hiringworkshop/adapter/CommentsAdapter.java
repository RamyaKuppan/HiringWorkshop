package com.example.hiringworkshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.hiringworkshop.R;
import com.example.hiringworkshop.adapter.listener.BaseRecyclerAdapterListener;
import com.example.hiringworkshop.adapter.viewholder.CommentsViewHolder;
import com.example.hiringworkshop.databinding.InflateCommentsBinding;
import com.example.hiringworkshop.model.response.CommentsData;

import java.util.List;

public class CommentsAdapter extends BaseRecyclerAdapter<CommentsData, InflateCommentsBinding, CommentsViewHolder> {

    private BaseRecyclerAdapterListener<CommentsData> listener;

    public CommentsAdapter(List<CommentsData> data, BaseRecyclerAdapterListener<CommentsData> listener) {
        super(data);
        this.listener = listener;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.inflate_comments, parent, false);
        return new CommentsViewHolder(binding, listener);
    }
}