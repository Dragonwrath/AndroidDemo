package com.apache.volley.toolbox.advance.request;


import android.graphics.BitmapFactory;

import com.apache.volley.response.NetworkResponse;
import com.apache.volley.response.Response.*;
import com.apache.volley.response.Response;
import com.apache.volley.toolbox.advance.HttpHeaderParser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class JsonObjectRequest extends JsonRequest<JSONObject> {
    public JsonObjectRequest(int method, String url, JSONObject jsonRequest,
                             Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                errorListener);
    }

    public JsonObjectRequest(String url, JSONObject jsonRequest, Listener<JSONObject> listener,
                             ErrorListener errorListener) {
        this(jsonRequest == null ? Method.GET : Method.POST, url, jsonRequest,
                listener, errorListener);
    }


    @Override
    public Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString) ,HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
