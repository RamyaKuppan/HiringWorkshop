package com.example.hiringworkshop.modules.reply;

import com.example.hiringworkshop.mvp.Presenter;
import com.example.hiringworkshop.repositories.VideoCommentsRepository;

import java.util.Observable;

public class ReplyPresenter extends Presenter<ReplyPresenterCallback> {

    @Override
    protected void registerWithModel() {
        VideoCommentsRepository.getInstance().addObserver(this);
    }

    @Override
    protected void unregisterModels() {
        VideoCommentsRepository.getInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void getRepliesForComment(String commentId) {
        VideoCommentsRepository.getInstance().getRepliesForComment(commentId);
    }

    public void sendReply(String reply, String commentId) {
        VideoCommentsRepository.getInstance().sendReply(reply, commentId);
    }
}
