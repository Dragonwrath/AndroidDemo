package com.example.okhttpV3.offical_sample;

import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public final class Authenticate {
	private final OkHttpClient client;

	public Authenticate() {
		client = new OkHttpClient.Builder()
				.authenticator(new Authenticator() {
					@Override public Request authenticate(Route route, Response response) throws IOException {
						if (response.request().header("Authorization") != null) {
							return null; // Give up, we've already attempted to authenticate.
						}

						System.out.println("Authenticating for response: " + response);
						System.out.println("Challenges: " + response.challenges());
						String credential = Credentials.basic("jesse", "password1");
						return response.request().newBuilder()
								.header("Authorization", credential)
								.build();
					}
				})
				.build();
	}

	public void run() throws Exception {
		Request request = new Request.Builder()
				.url("http://publicobject.com/secrets/hellosecret.txt")
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

			System.out.println(response.body().string());
		}
	}

	public static void main(String... args) throws Exception {
		new Authenticate().run();
	}
}