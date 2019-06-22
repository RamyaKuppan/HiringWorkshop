package com.example.hiringworkshop.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hiringworkshop.OnReplyClicked;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.models.CommentsModel;

import java.util.List;

import retrofit2.Response;

/**
 * CommentsListAdapter
 * This adapter class is for setting up the comments list to the recycler view
 */
public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.CommentsViewHolder> {


    private static Context context;

    private static List<CommentsModel> mCommentsModel;

    private static OnReplyClicked mOnReplyClicked;

    /**
     * parameterised constructor
     *
     * @param mContext context
     * @param comments comments
     */
    public CommentsListAdapter(Context mContext, Response<List<CommentsModel>> comments, OnReplyClicked onReplyClicked) {
        context = mContext;
        mCommentsModel = comments.body();
        mOnReplyClicked = onReplyClicked;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CommentsViewHolder(LayoutInflater.from(context).inflate(R.layout.row_comments, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder commentsViewHolder, int i) {
        commentsViewHolder.tvUserName.setText(mCommentsModel.get(i).getUser());
        commentsViewHolder.tvComments.setText(mCommentsModel.get(i).getComment());
        if (mCommentsModel.get(i).getReplies().size() > 0) {
            commentsViewHolder.tvReply.setVisibility(View.VISIBLE);
            commentsViewHolder.tvReply.setText(mCommentsModel.get(i).getReply());
        }
    }

    @Override
    public int getItemCount() {
        return mCommentsModel.size();
    }

    /**
     * CommentsViewHolder
     * View Holder for the CommentsListAdapter
     */
    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName;

        TextView tvComments;

        TextView tvReply;

        /**
         * constructor for view holder
         *
         * @param itemView item view
         */
        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_name);
            tvComments = itemView.findViewById(R.id.tv_comments);
            tvComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnReplyClicked.onReplyClicked(getAdapterPosition());
                }
            });
            tvReply = itemView.findViewById(R.id.tv_reply_comments);
        }
    }
}
