package com.example.hiringworkshop.model;

public interface IWebserviceListener {

    void onSuccessfulApi(String response);
    void onFailureApi(String error);

}
