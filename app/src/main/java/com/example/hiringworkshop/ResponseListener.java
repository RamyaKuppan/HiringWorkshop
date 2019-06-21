package com.example.hiringworkshop;

import retrofit2.Response;

public interface ResponseListener {

   <T> void onResponse(Response<T> response, String video_detail);

   void onFailureResponse();
}
