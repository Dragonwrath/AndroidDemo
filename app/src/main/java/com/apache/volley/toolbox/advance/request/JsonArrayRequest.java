package com.apache.volley.toolbox.advance.request;

import com.apache.volley.error.ParseError;
import com.apache.volley.response.NetworkResponse;
import com.apache.volley.response.Response;
import com.apache.volley.response.Response.*;
import com.apache.volley.toolbox.advance.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;


public class JsonArrayRequest  extends JsonRequest<JSONArray>{
    public JsonArrayRequest(String url, Listener<JSONArray> listener, ErrorListener errorListener) {
        super(Method.GET, url, null, listener, errorListener);
    }

    @Override
    public Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONArray(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
