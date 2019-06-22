package com.example.hiringworkshop.fragment.video;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hiringworkshop.VideoApplication;
import com.example.hiringworkshop.fragment.video.comment.CommentsAdapter;
import com.example.hiringworkshop.model.Comment;
import com.example.hiringworkshop.model.User;
import com.example.hiringworkshop.model.request.CommentRequestData;
import com.example.hiringworkshop.model.response.CommentResponseData;
import com.example.hiringworkshop.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoFragmentViewModel extends ViewModel {

    public ObservableField<String> etCommentField = new ObservableField<>();
    public RetrofitClient mRetrofitClient = RetrofitClient.getInstance();
    public MutableLiveData<List<Comment>> commentsLiveData = new MutableLiveData<>();
    public CommentsAdapter mAdapter;

    public VideoFragmentViewModel(){
        mAdapter = new CommentsAdapter(this);
    }

    public MutableLiveData<List<Comment>> getCommentsLiveData() {
        Call<List<CommentResponseData>> request = mRetrofitClient.getService().getComments();
        request.enqueue(new Callback<List<CommentResponseData>>() {
            @Override
            public void onResponse(Call<List<CommentResponseData>> call, Response<List<CommentResponseData>> response) {
                List<Comment> comments = new ArrayList<>();
                for(CommentResponseData res : response.body()){
                    Comment c = new Comment();
                    c.setId(res.getId());
                    c.setComment(res.getComment());
                    c.setTimestamp(res.getTimestamp());
                    c.setUser(new User(res.getName()));
                    comments.add(c);
                }
                commentsLiveData.setValue(comments);
            }

            @Override
            public void onFailure(Call<List<CommentResponseData>> call, Throwable t) {
                Log.e("get comments error ", t.getLocalizedMessage());
            }
        });
        return commentsLiveData;
    }

    public void setCommentsLiveData(MutableLiveData<List<Comment>> commentsLiveData) {
        this.commentsLiveData = commentsLiveData;
    }

    public void notifyCommentsAdapter(List<Comment> comments) {
        mAdapter.submitList(new ArrayList<>(comments));
    }

    public CommentsAdapter getAdapter() {
        return mAdapter;
    }

    public Comment getCommentsAt(int position) {
        if (getComments() != null && getComments().size() > position) {
            return getComments().get(position);
        }
        return null;
    }

    public List<Comment> getComments(){
        return commentsLiveData.getValue();
    }

    public void postComment(){
        User user = VideoApplication.getUserInfo();
        final CommentRequestData commentReq = new CommentRequestData(user.getName(), etCommentField.get());

        Call<Object> request = mRetrofitClient.getService().postComment(commentReq);
        request.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Comment comment = new Comment();
                comment.setUser(new User(commentReq.getUser()));
                comment.setTimestamp(System.currentTimeMillis());
                comment.setId(String.valueOf(System.currentTimeMillis()));
                comment.setComment(commentReq.getComment());
                getComments().add(comment);
                commentsLiveData.setValue(getComments());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("post comment error: ", t.getLocalizedMessage());
            }
        });
    }
}