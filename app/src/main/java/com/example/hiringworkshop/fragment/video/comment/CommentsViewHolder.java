package com.example.hiringworkshop.fragment.video.comment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiringworkshop.databinding.ItemCommentBinding;
import com.example.hiringworkshop.fragment.video.VideoFragmentViewModel;

public class CommentsViewHolder extends RecyclerView.ViewHolder {

    private VideoFragmentViewModel mViewModel;
    private ItemCommentBinding mBinder;

    public CommentsViewHolder(VideoFragmentViewModel viewModel, @NonNull ItemCommentBinding binder) {
        super(binder.getRoot());
        this.mViewModel = viewModel;
        this.mBinder = binder;
    }

    void bind(int position){
        mBinder.setComment(mViewModel.getCommentsAt(position));
        mBinder.executePendingBindings();
    }
}
