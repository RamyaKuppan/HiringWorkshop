package com.example.hiringworkshop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hiringworkshop.Models.CommentData;
import com.example.hiringworkshop.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    private List<CommentData> commentDataList;

    class CommentHolder extends RecyclerView.ViewHolder {
        TextView commentTV;

        CommentHolder(View view) {
            super(view);
            commentTV = (TextView) view.findViewById(R.id.textView_comment);
        }
    }


    public CommentAdapter(List<CommentData> moviesList) {
        this.commentDataList = moviesList;
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_list_row, parent, false);

        return new CommentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        CommentData commentData = commentDataList.get(position);
        holder.commentTV.setText(commentData.getComment());

    }

    @Override
    public int getItemCount() {
        return commentDataList.size();
    }
}