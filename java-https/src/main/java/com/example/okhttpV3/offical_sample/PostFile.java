package com.example.okhttpV3.offical_sample;

import java.io.File;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public final class PostFile {
	public static final MediaType MEDIA_TYPE_MARKDOWN
			= MediaType.parse("text/x-markdown; charset=utf-8");

	private final OkHttpClient client = new OkHttpClient.Builder()
			.addInterceptor(new Interceptor(){
				@Override
				public Response intercept(Chain chain) throws IOException {
					final Request request = chain.request();
					System.out.println("request = " + request.body());
					final Response response = chain.proceed(request);
					if(response.code() == 200) {
						final ResponseBody body = response.body();
						System.out.println("body = " + body);
					}
					return response;
				}
			})
			.build();

	private void run() throws Exception {
		File file = new File("README.md");
		if(!file.exists()) {
			//noinspection ALL
			file.createNewFile();
		}
		System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());

		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

			System.out.println(response.body().string());
		}
	}

	public static void main(String... args) throws Exception {
		new PostFile().run();
	}
}