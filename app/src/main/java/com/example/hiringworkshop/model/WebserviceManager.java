package com.example.hiringworkshop.model;


import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.hiringworkshop.model.request.SendCommentsRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebserviceManager<T> extends Request<String> {

    Type responseType;
    T data;
    List<T> listOfData;
    SendCommentsRequest object;
    private IWebserviceListener listener;

    public WebserviceManager(int method, String url, Type responseType, @Nullable IWebserviceListener listener) {
        super(method, url, null);
        this.listener = listener;
        this.responseType = responseType;
    }

    public void post(SendCommentsRequest obj) {
        this.object = obj;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return object.toString().getBytes();
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
     * Converting String response to Respective Model Type
     */

    private T getResponseData(String response, Type responseType) {
        return new Gson().fromJson(response, responseType);
    }

    private List<T> getListResponseData(String response, Type responseType) {
        return new Gson().fromJson(response, responseType);
    }


}
