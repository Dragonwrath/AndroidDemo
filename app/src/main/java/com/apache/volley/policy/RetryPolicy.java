
package com.apache.volley.policy;

import com.apache.volley.error.VolleyError;

public interface RetryPolicy {

    public int getCurrentTimeout();

    public int getCurrentRetryCount();

    public void retry(VolleyError error) throws VolleyError;
}
