package com.example.hiringworkshop.model;


import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

public class WebserviceManager extends Request<String> {

    private IWebserviceListener listener;

    public WebserviceManager(@Nullable IWebserviceListener listener) {
        super(Method.GET, Constants.HttpUrls.URL, null);
        this.listener = listener;
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
        if (response!=null && !response.isEmpty())
            listener.onSuccessfulApi(response);

    }


    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        listener.onFailureApi(error.toString());
    }

}
