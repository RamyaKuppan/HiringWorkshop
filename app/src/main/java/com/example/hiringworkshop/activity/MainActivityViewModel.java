package com.example.hiringworkshop.activity;


import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hiringworkshop.VideoApplication;
import com.example.hiringworkshop.ViewModelCommunicator;
import com.example.hiringworkshop.fragment.video.comment.CommentsAdapter;
import com.example.hiringworkshop.model.Channel;
import com.example.hiringworkshop.model.Comment;
import com.example.hiringworkshop.model.Details;
import com.example.hiringworkshop.model.response.DetailsResponseData;
import com.example.hiringworkshop.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    public ObservableField<String> likeBtnText = new ObservableField<>();
    public ObservableField<String> subscribeBtnText = new ObservableField<>();
    public ObservableField<Details> details = new ObservableField<>();

    private RetrofitClient mRetrofitClient = VideoApplication.getRetrofitClient();
    private ViewModelCommunicator mCommunicator;

    public Details getDetails() {
        return details.get();
    }

    public void setDetails(Details details) {
        this.details.set(details);
    }

    public void onLikeBtnClick() {
        getDetails().setLiked(!getDetails().isLiked());
        getLikeBtnText();
    }

    private void getLikeBtnText() {
        if (getDetails() != null) {
            String text = (getDetails().isLiked()) ? "Unlike" : "Like";
            likeBtnText.set(text);
        } else {
            likeBtnText.set("Like");
        }
    }

    private void getSubscribeBtnText() {
        if (getDetails() != null && getDetails().getChannelInfo() != null) {
            String text = (getDetails().getChannelInfo().isSubscribed()) ? "Unsubscribe" : "Subscribe";
            subscribeBtnText.set(text);
        } else {
            subscribeBtnText.set("Subscribe");
        }
    }

    public void getData() {
        Call<DetailsResponseData> request = mRetrofitClient.getService().getDetails();
        request.enqueue(new Callback<DetailsResponseData>() {
            @Override
            public void onResponse(Call<DetailsResponseData> call, Response<DetailsResponseData> response) {
                DetailsResponseData vDetails = response.body();

                if (vDetails != null) {
                    Details details = new Details();
                    details.setImage(vDetails.getImage());
                    details.setDescription(vDetails.getDescription());

                    Channel channel = new Channel();
                    channel.setChannel(vDetails.getChannel());
                    channel.setChannelSubscribers(String.valueOf(vDetails.getChannelSubscribers()));
                    channel.setChannelOwner(vDetails.getChannelOwner());

                    details.setChannelInfo(channel);
                    details.setUploader(vDetails.getUploader());
                    details.setViews(vDetails.getViews());
                    details.setUploadedTimeline(vDetails.getUploadedTimeline());

                    setDetails(details);
                    getLikeBtnText();
                    getSubscribeBtnText();
                    mCommunicator.loadVideoFragment();
                }
            }

            @Override
            public void onFailure(Call<DetailsResponseData> call, Throwable t) {
                Log.e("get details error ", t.getLocalizedMessage());
            }
        });
    }

    public void setCommunicator(ViewModelCommunicator communicator) {
        this.mCommunicator = communicator;
    }

    public void onChannelClick() {
        this.mCommunicator.loadChannelFragment();
    }

    public void onSubscribeCtnClick() {
        getDetails().getChannelInfo().setSubscribed(!getDetails().getChannelInfo().isSubscribed());
        getSubscribeBtnText();
    }
}
