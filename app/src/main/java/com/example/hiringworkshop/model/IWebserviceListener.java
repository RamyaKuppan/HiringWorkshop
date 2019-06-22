package com.example.hiringworkshop.model;

import java.util.List;

public interface IWebserviceListener<T> {

    void onSuccessfulApi(T response);
    void onSuccessfulListApi(List<T> response);
    void onFailureApi(String error);

}
