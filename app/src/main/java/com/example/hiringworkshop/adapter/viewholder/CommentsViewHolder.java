package com.example.hiringworkshop.adapter.viewholder;

import com.example.hiringworkshop.adapter.listener.BaseRecyclerAdapterListener;
import com.example.hiringworkshop.databinding.InflateCommentsBinding;
import com.example.hiringworkshop.model.response.CommentsData;

public class CommentsViewHolder extends BaseViewHolder<CommentsData, InflateCommentsBinding> {

    private BaseRecyclerAdapterListener<CommentsData> listener;

    public CommentsViewHolder(InflateCommentsBinding binding, BaseRecyclerAdapterListener<CommentsData> listener) {
        super(binding.getRoot(), binding);
        this.listener = listener;
    }

    @Override
    void populateData() {
        binding.setCommentsData(data);
        binding.executePendingBindings();
    }

}
