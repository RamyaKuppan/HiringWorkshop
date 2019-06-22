package com.example.hiringworkshop.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hiringworkshop.DatabaseRepo.RealmDB;
import com.example.hiringworkshop.DatabaseRepo.VideoTable;
import com.example.hiringworkshop.R;
import com.example.hiringworkshop.ResponseListener;
import com.example.hiringworkshop.models.CommentsModel;
import com.example.hiringworkshop.models.VideoModel;
import com.example.hiringworkshop.utils.NetworkUtil;
import com.example.hiringworkshop.viewmodel.VideoDescriptionViewModel;

import java.util.List;

import io.realm.Realm;
import retrofit2.Response;

public class VideoDescriptionActivity extends AppCompatActivity implements
        ResponseListener, FragmentManager.OnBackStackChangedListener {

    private Button ivLike;

    private ImageView ivVideoThumbnail;

    private VideoDescriptionViewModel videoDescriptionViewModel;

    private Button btnSubscribe;

    private TextView tvChannelName;

    private VideoModel mVideoModel;

    private Handler fragmentHandler;

    private RecyclerView rvComments;

    private Button btnSubmitComments;

    private VideoTable videoTable;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RealmDB realmDB = new RealmDB(getApplicationContext());
        realmDB.setUpRealm();
        realm = Realm.getDefaultInstance();
        videoTable = new VideoTable(this);
        ivLike = findViewById(R.id.iv_like);
        fragmentHandler = new Handler();
        ivVideoThumbnail = findViewById(R.id.iv_video_thumbnail);
        tvChannelName = findViewById(R.id.tv_channel_desc);
        btnSubmitComments = findViewById(R.id.btn_submit_comment);
        btnSubscribe = findViewById(R.id.btn_subscribe);
        rvComments = findViewById(R.id.rv_list_comments);
        setOnClickListeners();
        videoDescriptionViewModel = ViewModelProviders.of(this).get(VideoDescriptionViewModel.class);
        videoDescriptionViewModel.setResponseListener(this);

        if (NetworkUtil.isNetworkConnected(this)) {
            videoDescriptionViewModel.getVideoDetails();
        } else {
            loadDataFromDB();
        }
        updateUIAfterOrientationChanges();
    }

    private void loadDataFromDB() {
        videoDescriptionViewModel.getVideoFromDB();
    }

    /**
     * set up UI
     */
    private void updateUIAfterOrientationChanges() {
        if (videoDescriptionViewModel.getLiked()) {
            ivLike.setText("Like");
        } else {
            ivLike.setText("UnLike");
        }
    }

    /**
     * set click listeners for the view
     */
    private void setOnClickListeners() {
        ivLike.setOnClickListener(v -> {
            if (ivLike.getText().equals("Like")) {
                ivLike.setText(getResources().getString(R.string.unlike));
                videoDescriptionViewModel.setLiked(false);
            } else {
                ivLike.setText("Like");
                videoDescriptionViewModel.setLiked(true);
            }
        });
        btnSubscribe.setOnClickListener(v -> {
            btnSubscribe.setText("Subscribed");
            if (mVideoModel != null)
                addFragment(ChannelInfoFragment.newInstance(mVideoModel), true);
        });

        btnSubmitComments.setOnClickListener(v -> {
            EditText etComments = findViewById(R.id.et_comments);
            String comments = etComments.getText().toString();
            if (!etComments.getText().toString().isEmpty()) {
                videoDescriptionViewModel.uploadComments(comments);
            } else {
                Toast.makeText(this, "Please type in the comments", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * helper method to the add the fragment to the activity
     *
     * @param fragment       fragment instance
     * @param addToBackStack add to back stack or not
     */
    private void addFragment(Fragment fragment, boolean addToBackStack) {
        Runnable mRunnable = () -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();

            getSupportFragmentManager().addOnBackStackChangedListener(this);
            fragmentTransaction.replace(R.id.container, fragment,
                    fragment.getClass().getName());
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().getName());
            }
            fragmentTransaction.commitAllowingStateLoss();
        };
        if (null != mRunnable) {
            fragmentHandler.post(mRunnable);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoDescriptionViewModel != null)
            videoDescriptionViewModel.removeListener();
    }

    @Override
    public <T> void onResponse(Response<T> response, String identifier) {
        if (response.body() != null && identifier.equals("Video Detail")) {
            Response<VideoModel> videoModelResponse = (Response<VideoModel>) response;
            mVideoModel = videoModelResponse.body();
            Glide.with(this).load(videoModelResponse.body().getImage()).into(ivVideoThumbnail);
            tvChannelName.setText(videoModelResponse.body().getChannel());
            mVideoModel.setId(1);
            videoTable.writeVideo(mVideoModel);
        } else if (response.body() != null && identifier.equals("comments")) {
            Response<List<CommentsModel>> comments = (Response<List<CommentsModel>>) response;
            setCommentsDataToUI(comments);
        }
    }

    /**
     * set comments data to the recycler view
     *
     * @param comments comments list
     */
    private void setCommentsDataToUI(Response<List<CommentsModel>> comments) {
        rvComments.setAdapter(new CommentsListAdapter(this, comments));
        rvComments.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onFailureResponse() {
        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDBDataReady(VideoModel videoModel) {
        if (videoModel != null) {
            //data is already stored in the DB
            Glide.with(this).load(videoModel.getImage()).into(ivVideoThumbnail);
            tvChannelName.setText(videoModel.getChannel());
        } else {
            Toast.makeText(this, "Data back up is not available", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackStackChanged() {
        //yet to implement
    }
}
