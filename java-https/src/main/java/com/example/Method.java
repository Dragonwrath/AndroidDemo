package com.example;


public enum Method{
	POST("POST"),GET("GET");

	private String mName;
	Method(String methodName) {
		mName = methodName;
	}

	public String getName() {
		return mName;
	}
}
