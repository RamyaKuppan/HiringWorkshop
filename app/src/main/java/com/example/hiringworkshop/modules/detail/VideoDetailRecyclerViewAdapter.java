package com.example.hiringworkshop.modules.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hiringworkshop.R;
import com.example.hiringworkshop.restApi.restApiModels.VideoComment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoDetailRecyclerViewAdapter extends RecyclerView.Adapter<CommentsViewHolder> {

    private List<VideoComment> videoCommentList;

    public VideoDetailRecyclerViewAdapter(List<VideoComment> videoCommentList) {
        this.videoCommentList = videoCommentList;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View commentsItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false);
        return new CommentsViewHolder(commentsItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder commentsViewHolder, int i) {
        VideoComment videoComment = videoCommentList.get(i);
        commentsViewHolder.setDataToView(videoComment);
    }

    @Override
    public int getItemCount() {
        return videoCommentList.size();
    }

    public void setAndUpdate(List<VideoComment> videoCommentList) {
        this.videoCommentList = videoCommentList;
        //TODO Use diffUtil to smart update.
        notifyDataSetChanged();
    }
}


class CommentsViewHolder extends RecyclerView.ViewHolder {

    private TextView nameText;
    private TextView commentText;

    public CommentsViewHolder(@NonNull View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.user_name);
        commentText = itemView.findViewById(R.id.comment);
    }

    public void setDataToView(VideoComment videoComment) {
        nameText.setText(videoComment.getName());
        commentText.setText(videoComment.getComment());
    }
}
