package com.example.okhttpV3.offical_sample;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.Handshake;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class CertificatePinning {
	private final OkHttpClient client;

	public CertificatePinning() {
		client = new OkHttpClient.Builder()
				.certificatePinner(
						new CertificatePinner.Builder()
								.add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
								.build())
				.build();
	}

	public void run() throws Exception {
		Request request = new Request.Builder()
				.url("https://publicobject.com/robots.txt")
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

			for (Certificate certificate : response.handshake().peerCertificates()) {
				System.out.println(CertificatePinner.pin(certificate));
			}
		}
	}

	public void test()  throws Exception{
		String hostname = "publicobject.com";
		CertificatePinner certificatePinner = new CertificatePinner.Builder()
				.add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ii=")
				//				.add(hostname, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
				.build();
		OkHttpClient client =  new OkHttpClient.Builder()
				.certificatePinner(certificatePinner)
				.addNetworkInterceptor(new Interceptor(){
					@Override
					public Response intercept(Chain chain) throws IOException {
						final Connection connection = chain.connection();
						assert connection != null;
						final Handshake handshake = connection.handshake();
						assert handshake != null;
						final List<Certificate> certificates = handshake.peerCertificates();
						for(Certificate certificate : certificates) {
							System.out.println(certificate.getType() + certificate.getPublicKey());
						}
						final Request request = chain.request();
						return chain.proceed(request);
					}
				})
				.build();

		Request request = new Request.Builder()
				.url("https://" + hostname)
				.build();
		client.newCall(request).execute();

	}

	public static void main(String... args) throws Exception {
		new CertificatePinning().test();
	}
}


final class CheckHandshake {
	/** Rejects otherwise-trusted certificates. */
	private static final Interceptor CHECK_HANDSHAKE_INTERCEPTOR = new Interceptor() {
		Set<String> blacklist = Collections.singleton(
				"sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=");

		@Override public Response intercept(Chain chain) throws IOException {
			final Handshake handshake = chain.connection().handshake();
			final List<Certificate> list = handshake.peerCertificates();
			for (Certificate certificate : list) {
				String pin = CertificatePinner.pin(certificate);
				if (blacklist.contains(pin)) {
					throw new IOException("Blacklisted peer certificate: " + pin);
				}
			}
			return chain.proceed(chain.request());
		}
	};

	private final OkHttpClient client = new OkHttpClient.Builder()
			.addNetworkInterceptor(CHECK_HANDSHAKE_INTERCEPTOR)
			.build();

	public void run() throws Exception {
		Request request = new Request.Builder()
				.url("https://publicobject.com/helloworld.txt")
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

			System.out.println(response.body().string());
		}
	}

	public static void main(String... args) throws Exception {
		new CheckHandshake().run();
	}
}
