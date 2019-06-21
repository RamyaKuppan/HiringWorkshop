package com.example.hiringworkshop.activity;


import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.hiringworkshop.VideoApplication;
import com.example.hiringworkshop.ViewModelCommunicator;
import com.example.hiringworkshop.model.Channel;
import com.example.hiringworkshop.model.Details;
import com.example.hiringworkshop.network.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    public ObservableField<String> likeBtnText = new ObservableField<>();
    public ObservableField<String> suscribeBtnText = new ObservableField<>();
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
        if(getDetails() != null) {
            String text = (getDetails().isLiked()) ? "Unlike" : "Like";
            likeBtnText.set(text);
        } else {
            likeBtnText.set("Like");
        }
    }

    private void getSubscribeBtnText() {
        if(getDetails() != null && getDetails().getChannelInfo() != null) {
            String text = (getDetails().getChannelInfo().isSubscribed()) ? "Unsubscribe" : "Subscribe";
            suscribeBtnText.set(text);
        } else {
            suscribeBtnText.set("Subscribe");
        }
    }

    public void getData() {
        Call<JSONObject> request = mRetrofitClient.getService().getDetails();
        request.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                JSONObject object = response.body();
                Details details = new Details();
                Channel channel = new Channel();

                try {
                    details.setImage(object.getString("image"));
                    details.setDescription(object.getString("description"));
                    channel.setChannelSubscribers(object.getLong("channelSubscribers"));
                    channel.setChannelOwner(object.getString("channelOwner"));
                    details.setChannelInfo(channel);
                    details.setUploader(object.getString("uploader"));
                    details.setViews(object.getString("views"));
                    details.setUploadedTimeline(object.getString("uploadedTimeline"));
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

                setDetails(details);
                getLikeBtnText();
                getSubscribeBtnText();
                mCommunicator.loadVideoFragment();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

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
