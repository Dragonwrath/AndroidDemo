package com.apache.volley.toolbox.advance.request;

import com.apache.volley.request.Request;
import com.apache.volley.response.NetworkResponse;
import com.apache.volley.response.Response;
import com.apache.volley.toolbox.advance.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

public class StringRequest extends Request<String> {
    private final Response.Listener<String> mListener;

    public StringRequest(int method, String url, Response.Listener<String> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    public StringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    @Override
    public void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    public Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
