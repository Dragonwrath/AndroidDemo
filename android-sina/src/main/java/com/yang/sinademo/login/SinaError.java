package com.yang.sinademo.login;


public class SinaError {

    /**
     * error : source paramter(appkey) is missing
     * error_code : 10006
     * request : /2/users/show.json
     */

    private String error;
    private int error_code;
    private String request;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
