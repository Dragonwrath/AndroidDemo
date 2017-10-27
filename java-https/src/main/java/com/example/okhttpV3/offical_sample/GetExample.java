package com.example.okhttpV3.offical_sample;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class GetExample{
	private OkHttpClient client = new OkHttpClient.Builder()
			//			.proxy(new Proxy(HTTP,new InetSocketAddress("localhost",8888)))
			.readTimeout(3,TimeUnit.SECONDS).addNetworkInterceptor(new Interceptor(){
				@Override
				public Response intercept(Chain chain) throws IOException {
					final Request originalRequest = chain.request();
					final Headers headers = originalRequest.headers();
					for(int i = 0;i < headers.size();i++) {
						System.out.println(headers.name(i) + "@@@@" + headers.value(i));
					}

					return chain.proceed(originalRequest);
				}
			}).build();
	private Callback callback = new Callback(){
		@Override
		public void onFailure(Call call,IOException e) {
			e.printStackTrace();
		}

		@Override
		public void onResponse(Call call,Response response) throws IOException {
			final ResponseBody body = response.body();
			if(body != null) {
				final InputStream stream = body.byteStream();
				byte[] cache = new byte[1024];
				int len;
				while((len = stream.read(cache)) > 0) {
					System.out.println(new String(cache,0,len));
				}
			}
		}
	};

	String run(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		try(Response response = client.newCall(request).execute()) {
			if(response.code() == 200) {
				final Headers headers = response.headers();
				final int size = headers.size();
				for(int i = 0;i < size;i++) {
					System.out.println(headers.name(i) + "----" + headers.value(i));
				}
				final String cache = response.cacheControl().toString();
				System.out.println("cache = " + cache);
				final ResponseBody body = response.body();
				if(body != null) {
					System.out.println("body.contentType() = " + body.contentType());
					System.out.println("body.contentLength() = " + body.contentLength());
				}
				final String message = response.message();
				System.out.println("message = " + message);
			}
			return response.body().string();
		}
	}

	void run(String url,Callback callback) {
		final Request request = new Request.Builder().get().url(url).build();
		client.newCall(request).enqueue(callback);
	}

	private void addHeaders() throws IOException {
		final Request request = new Request.Builder().url("https://api.github.com/repos/square/okhttp/issues").header("User-Agent","OkHttp Headers.java").addHeader("Accept","application/json; q=0.5").addHeader("Accept","application/vnd.github.v3+json").build();
		try(Response response = client.newCall(request).execute()) {
			if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);
			final Headers headers = response.headers();
			for(int i = 0;i < headers.size();i++) {
				System.out.println("headers.name[i] 	= " + headers.name(i));
				System.out.println("headers.value[i]  = " + headers.value(i));
				System.out.println();
			}

		}

	}

	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

	public void postString() throws Exception {
		String postBody = "" + "Releases\n" + "--------\n" + "\n" + " * _1.0_ May 6, 2013\n" + " * _1.1_ June 15, 2013\n" + " * _1.2_ August 11, 2013\n";

		Request request = new Request.Builder().url("https://api.github.com/markdown/raw").post(RequestBody.create(MEDIA_TYPE_MARKDOWN,postBody)).build();

		try(Response response = client.newCall(request).execute()) {
			if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);
			System.out.println(response.body().string());
		}
	}

	public static void main(String[] args) throws Exception {
		GetExample example = new GetExample();
		//		String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
		//		System.out.println(response);
		//		example.run("http://publicobject.com/helloworld.txt",example.callback);
		//		example.addHeaders();
		//		example.sout();
		example.postString();
	}

	void sout() throws IOException {
		final Properties properties = System.getProperties();

		final String proxyHost = (String)properties.remove("http.proxyHost");
		final String proxyPort = (String)properties.remove("http.proxyPort");
		System.out.println("proxyHost = " + proxyHost);
		System.out.println("proxyPort = " + proxyPort);
		String url = "";
		HttpsURLConnection connection = (HttpsURLConnection)new URL(url).openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		connection.getInputStream();
	}
}

