package com.example.hiringworkshop.adapter.viewholder;

import android.content.Context;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T, DB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public T data;
    protected DB binding;
    protected Context mContext;
    String TAG = getClass().getSimpleName();

    BaseViewHolder(View itemView) {
        super(itemView);
    }

    BaseViewHolder(View itemView, DB binding) {
        super(itemView);
        this.binding = binding;
        mContext = binding.getRoot().getContext();
    }

    public void setData(T data) {
        this.data = data;
        populateData();
    }

    abstract void populateData();
}
