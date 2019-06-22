package com.example.hiringworkshop.model;


import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

public class WebserviceManager<T> extends Request<String> {

    Type responseType;
    T data;
    List<T> listOfData;
    private IWebserviceListener listener;

    public WebserviceManager(int method, String url, Type responseType, @Nullable IWebserviceListener listener) {
        super(method, url, null);
        this.listener = listener;
        this.responseType = responseType;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String rawResponse = null;
        try {
            rawResponse = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Response.success(rawResponse, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        if (response != null && !response.isEmpty()) {

            char c = response.charAt(0);

            if (c == '[') {
                listOfData = getListResponseData(response, responseType);
                if (listOfData != null) {
                    listener.onSuccessfulListApi(listOfData);
                }
            } else {
                data = getResponseData(response, responseType);
                if (data != null) {
                    listener.onSuccessfulApi(data);
                }
            }
        }
    }


    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        listener.onFailureApi(error.toString());
    }


    /**
     *  Converting String response to Respective Model Type
     * */

    private T getResponseData(String response, Type responseType) {
        return new Gson().fromJson(response, responseType);
    }

    private List<T> getListResponseData(String response, Type responseType) {
        return new Gson().fromJson(response, responseType);
    }


}
