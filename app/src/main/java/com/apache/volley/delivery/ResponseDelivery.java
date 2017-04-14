package com.apache.volley.delivery;


import com.apache.volley.error.VolleyError;
import com.apache.volley.request.Request;
import com.apache.volley.response.Response;

public interface ResponseDelivery {
    public void postResponse(Request<?> request, Response<?> response);
    public void postResponse(Request<?> request, Response<?> response, Runnable runnable);
    public void postError(Request<?> request, VolleyError error);

}
