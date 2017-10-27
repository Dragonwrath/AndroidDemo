package com.apache.volley.network;


import com.apache.volley.error.VolleyError;
import com.apache.volley.request.Request;
import com.apache.volley.response.NetworkResponse;

public interface Network {
    NetworkResponse performRequest(Request<?> request)  throws VolleyError;
}
