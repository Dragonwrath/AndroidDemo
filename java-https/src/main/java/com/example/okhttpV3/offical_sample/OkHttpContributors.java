package com.example.okhttpV3.offical_sample;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;



public class OkHttpContributors{
	public static final String ENDPOINT = "https://api.github.com/repos/square/okhttp/contributors";

	public static final Gson GSON = new Gson();
	public static class Contributor {
		@SerializedName("login") public String login;
		@SerializedName("contributions") public int contributions;
	}

	public static void main(String... args) throws Exception {
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(new Interceptor(){
					@Override
					public Response intercept(Chain chain) throws IOException {
						final Request request = chain.request();
						Headers headers = request.headers();
						System.out.println("----request header---");
						for(int i = 0;i < headers.size();i++) {
							System.out.println(headers.name(i) + "--" + headers.value(i));
						}
						final Response response = chain.proceed(request);
						headers = response.headers();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println("----response header---");
						for(int i = 0;i < headers.size();i++) {
							System.out.println(headers.name(i) + "--" + headers.value(i));
						}
						System.out.println();
						System.out.println();
						System.out.println();

						return response;
					}
				})
				.build();

		// Create request for remote resource.
		Request request = new Request.Builder()
				.url(ENDPOINT)
				.build();

		// Execute the request and retrieve the response.
		try (Response response = client.newCall(request).execute()) {
			// Deserialize HTTP response to concrete type.
			ResponseBody body = response.body();
			final TypeAdapter<List<Contributor>> adapter = GSON.getAdapter(new TypeToken<List<Contributor>>(){});
			List<Contributor> contributors = adapter.fromJson(body.charStream());

			// Sort list by the most contributions.
			Collections.sort(contributors, new Comparator<Contributor>() {
				@Override public int compare(Contributor c1, Contributor c2) {
					return c2.contributions - c1.contributions;
				}
			});

			// Output list of contributors.
			for (Contributor contributor : contributors) {
				System.out.println(contributor.login + ": " + contributor.contributions);
			}
		}
	}

	private OkHttpContributors() {
		// No instances.
	}
}
