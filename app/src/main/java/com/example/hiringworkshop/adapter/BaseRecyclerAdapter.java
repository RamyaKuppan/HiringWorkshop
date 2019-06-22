package com.example.hiringworkshop.adapter;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hiringworkshop.BR;
import com.example.hiringworkshop.adapter.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

abstract class BaseRecyclerAdapter<T, DB extends ViewDataBinding, VH extends BaseViewHolder<T, DB>> extends RecyclerView.Adapter<VH> {

    protected String TAG = getClass().getSimpleName();
    protected DB binding;
    private List<T> data;

    BaseRecyclerAdapter(List<T> data) {
        this.data = data;
    }


    @Override
    public void onBindViewHolder(@NotNull VH holder, int position) {
        binding.setVariable(BR.holder, holder);
        binding.executePendingBindings();
        if (!getData().get(position).equals(""))
            holder.setData(getItem(position));

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public T getItem(int position) throws IndexOutOfBoundsException {
        return data.get(position);
    }

    public void addItem(T object) {
        data.add(object);
        notifyItemInserted(data.indexOf(object));
    }

    public void removeItem(int position) throws IndexOutOfBoundsException {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void resetItems(List<T> data, int position) {
        if (data != null) {
            this.data = data;
            notifyItemChanged(position, data.size());
        }
    }

    public void resetItems(List<T> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public void setData(int position, T data) {
        if (data != null) {
            this.data.set(position, data);
            notifyItemChanged(position);
        }
    }

    public void addItems(List<T> data) {
        if (data != null) {
            int startRange = (this.data.size() - 1) > 0 ? data.size() - 1 : 0;
            this.data.addAll(data);
            notifyItemRangeChanged(startRange, data.size());
        }
    }

    public List<T> getData() {
        return data;
    }

}

