package com.apache.volley.toolbox.base.stack;


import com.apache.volley.error.AuthFailureError;
import com.apache.volley.request.Request;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

public interface HttpStack {
    HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders)
            throws IOException, AuthFailureError;
}
