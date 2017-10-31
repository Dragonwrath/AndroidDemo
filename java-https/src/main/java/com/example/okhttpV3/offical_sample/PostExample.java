package com.example.okhttpV3.offical_sample;

import java.io.File;
import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PostExample{
	public static final MediaType JSON
			= MediaType.parse("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();

	String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	String bowlingJson(String player1, String player2) {
		return "{'winCondition':'HIGH_SCORE',"
				+ "'name':'Bowling',"
				+ "'round':4,"
				+ "'lastSaved':1367702411696,"
				+ "'dateStarted':1367702378785,"
				+ "'players':["
				+ "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
				+ "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
				+ "]}";
	}

	public static void main(String[] args) throws IOException {
		PostExample example = new PostExample();
		String json = example.bowlingJson("Jesse", "Jake");
		String response = example.post("http://www.roundsapp.com/post", json);
		System.out.println(response);
	}

	public static final class PostFile {
		public static final MediaType MEDIA_TYPE_MARKDOWN
				= MediaType.parse("text/x-markdown; charset=utf-8");

		private final OkHttpClient client = getLoggingBuilder();

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
	private static OkHttpClient getLoggingBuilder() {
		return new OkHttpClient.Builder()
				.addInterceptor(new Interceptor(){
					@Override
					public Response intercept(Chain chain) throws IOException {
						final Request request = chain.request();
//						final Headers headers = request.headers();
//						final int size = headers.size();
//						for(int i = 0;i < size;i++) {
//							System.out.println(headers.name(i) + headers.value(i));
//						}

						final Response response = chain.proceed(request);
						if(response.code() == 200) {
							final ResponseBody body = response.body();
							System.out.println("body = " + body);
						}
						return response;
					}
				})
				.build();
	}

	public final static class PostForm {
		private final OkHttpClient client = getLoggingBuilder();

		public void run() throws Exception {
			RequestBody formBody = new FormBody.Builder()
					.add("search", "Jurassic Park")
					.build();
			Request request = new Request.Builder()
					.url("https://en.wikipedia.org/w/index.php")
					.post(formBody)
					.build();

			try (Response response = client.newCall(request).execute()) {
				if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

//				System.out.println(response.body().string());
			}
		}

		public static void main(String... args) throws Exception {
			new PostForm().run();
		}
	}

}
