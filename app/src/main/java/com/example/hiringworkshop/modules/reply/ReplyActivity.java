package com.example.hiringworkshop.modules.reply;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hiringworkshop.R;
import com.example.hiringworkshop.mvp.ActivityView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ReplyActivity extends ActivityView<ReplyPresenter, ReplyPresenterCallback> implements ReplyPresenterCallback {

    private static final String COMMENT_STR = "comment";
    private String commentId;

    public static void open(Context context, String comment) {
        Intent intent = new Intent(context, ReplyActivity.class);
        intent.putExtra(COMMENT_STR, comment);
        context.startActivity(intent);
    }

    private EditText replyText;

    private RecyclerView recyclerView;

    private Button replyBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        replyText = findViewById(R.id.reply_edit_text);
        recyclerView = findViewById(R.id.recycler_replies);
        replyBtn = findViewById(R.id.reply_btn);

        if (getIntent() != null) {
            commentId = getIntent().getStringExtra(COMMENT_STR);
        }

        //getPresenter().getRepliesForComment(commentId);


        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reply = replyText.getText().toString();

                getPresenter().sendReply(reply, commentId);
            }
        });
    }

    @Override
    protected ReplyPresenterCallback getCallback() {
        //Used weakreference internally.
        return this;
    }

    @Override
    protected ReplyPresenter createPresenter() {
        return new ReplyPresenter();
    }
}
