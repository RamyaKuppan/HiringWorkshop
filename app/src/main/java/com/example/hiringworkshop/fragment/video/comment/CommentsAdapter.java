package com.example.hiringworkshop.fragment.video.comment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.hiringworkshop.R;
import com.example.hiringworkshop.databinding.ItemCommentBinding;
import com.example.hiringworkshop.fragment.video.VideoFragmentViewModel;
import com.example.hiringworkshop.model.Comment;

public class CommentsAdapter extends ListAdapter<Comment, CommentsViewHolder> {

    private VideoFragmentViewModel mViewModel;

    public CommentsAdapter(VideoFragmentViewModel viewModel) {
        super(DIFF_CALLBACK);
        this.mViewModel = viewModel;
    }

    private static DiffUtil.ItemCallback<Comment> DIFF_CALLBACK = new DiffUtil.ItemCallback<Comment>() {
        @Override
        public boolean areItemsTheSame(@NonNull Comment oldItem, @NonNull Comment newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Comment oldItem, @NonNull Comment newItem) {
            return oldItem.equals(newItem);
        }
    };


    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCommentBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_comment, parent, false);
        return new CommentsViewHolder(mViewModel, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        holder.bind(position);
    }
}
