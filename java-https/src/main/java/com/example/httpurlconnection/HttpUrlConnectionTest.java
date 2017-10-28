package com.example.httpurlconnection;


import com.example.Method;
import com.example.StringUtils;
import com.example.okhttpV3.offical_sample.OkHttpContributors;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Credentials;

import static com.example.Method.GET;
import static com.example.Method.POST;


public class HttpUrlConnectionTest{
	private static final String AUTHENTICATE = "http://publicobject.com/secrets/hellosecret.txt";
	private static final String ZHIDAO_1 = "http://www.baidu.com//link?url=lSk2d3ZS978NBWsbY30svn9rQPYT1TiBnU_YMebfF-plkmGLJoMfbog9mrq9escYL3h5y-";
	private static final String ZHIDAO_2 = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%2F%2Fnoinspections&oq=%252F%252Fnoinspections%2520ResultOf&rsv_pq=88d7b7e40005e405&rsv_t=a1acJRv7hVb7wJAeCHP38RPaNvDsa9dJjHDu%2Fp52xUj23eZIglCcnJrMaTg&rqlang=cn&rsv_enter=0&inputT=2377&rsv_sug3=11&rsv_sug1=3&rsv_sug7=000&rsv_sug2=0&rsv_sug4=4481&rsv_sug=1";
	private static final String TRANSLATE_MARKDOWN = "https://api.github.com/markdown/raw";

	public static void main(String[] args) throws Exception {
		//		System.out.println(translateMarkDown());
		authenticate();
	}

	private static void authenticate() throws Exception {
		HttpURLConnection con = buildConnection(AUTHENTICATE);
//		con.setInstanceFollowRedirects(false);
		con.setInstanceFollowRedirects(true);

		con.connect();
		int code = con.getResponseCode();
		if(code == 200 ) printInputStream(con);
		else if(code == 301 ) {
			String newUrl = con.getHeaderField("Location");
			if(!StringUtils.isEmpty(newUrl)) {
				con.disconnect();
				con = buildHttpsConnection(newUrl);
				con.setRequestProperty("Authorization", Credentials.basic("jesse", "password1"));
				con.connect();
				code = con.getResponseCode();
				if(code == 200 ) printInputStream(con);
			}
			con.disconnect();
			final Map<String, List<String>> map = con.getRequestProperties();
			System.out.println("-----------request header-----------");
			for(String key : map.keySet()) {
				System.out.println("key = " + key);
				System.out.println("map.get = " + Arrays.toString(map.get(key).toArray()));
			}
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("-----------response header-----------");
		}
		else System.out.println(code + "---" + con.getResponseMessage());
	}

	private static void contributorPrint() throws Exception {
		final HttpURLConnection connection = buildConnection(OkHttpContributors.ENDPOINT,GET);
		connection.connect();
		final int code = connection.getResponseCode();
		if(code >= 200 && code < 300) {
			final TypeAdapter<List<OkHttpContributors.Contributor>> adapter = OkHttpContributors.GSON.getAdapter(new TypeToken<List<OkHttpContributors.Contributor>>(){
			});
			final List<OkHttpContributors.Contributor> list = adapter.fromJson(new InputStreamReader(connection.getInputStream()));
			for(OkHttpContributors.Contributor contributor : list) {
				System.out.println(String.format(Locale.getDefault(),"Name is %15s, Id is %d",contributor.login,contributor.contributions));
			}
		}
	}

	private static String translateMarkDown() throws Exception {
		String result = "";
		String postBody = "" + "Releases\n" + "--------\n" + "\n" + " * _1.0_ May 6, 2013\n" + " * _1.1_ June 15, 2013\n" + " * _1.2_ August 11, 2013\n";
		System.out.println(postBody);
		final HttpURLConnection con = buildConnection(TRANSLATE_MARKDOWN);
		con.setRequestProperty("Content-Type","text/x-markdown; charset=utf-8");
		//		con.setRequestProperty("Connection","Keep-Alive");
		//		con.setRequestProperty("Accept-Encoding","gzip");
		//		con.setRequestProperty("Host","api.github.com");
		//		con.setRequestProperty("User-Agent","okhttp/3.9.0");
		con.setDoOutput(true);
		con.setDoInput(true);
		//		con.setSSLSocketFactory();
		//		con.setSSLSocketFactory(new );
		OutputStream out = con.getOutputStream();
		out.write(postBody.getBytes());
		con.connect();
		final String message = con.getResponseMessage();
		System.out.println("message = " + message);
		final InputStream stream = con.getInputStream();
		final byte[] cache = new byte[1024];
		int len;
		while((stream.read(cache)) > 0) {
			System.out.println(new String(cache));
		}
		return result;
	}

	private static void connectiZhidao() throws Exception {
		final File file = new File("D:\\Samples\\Rxjava\\java8\\cache.html");
		if(!file.exists()) {
			//noinspection ResultOfMethodCallIgnored
			file.createNewFile();
		}
		final FileOutputStream out = new FileOutputStream(file);
		String x = URLDecoder.decode("https://zhidao.baidu.com/question/166627039.html?qbl=relate_question_0&word=%CB%F9%D3%D0%B5%C4%B8%DF%BC%B6%D3%EF%D1%D4%20C%D3%EF%D1%D4");
		x = new String(x.getBytes(),"gbk");
		System.out.println(x);
		final HttpURLConnection connection = buildConnection(ZHIDAO_2);
		setZhidao1(connection);
		connection.connect();
		final InputStream stream = connection.getInputStream();
		byte[] cache = new byte[1024];
		int len;
		while((len = stream.read(cache)) > 0) {
			out.write(cache,0,len);
		}
	}

	private static HttpURLConnection buildConnection(String path) throws Exception {
		return buildConnection(path,POST);
	}

	private static HttpURLConnection buildConnection(String path,Method method) throws Exception {
		final URL url = new URL(path);
		final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(10000);
		conn.setRequestMethod(method.getName());
		conn.setReadTimeout(10000);
		return conn;
	}

	private static HttpsURLConnection buildHttpsConnection(String path) throws Exception {
		return buildHttpsConnection(path, GET);
	}

	private static HttpsURLConnection buildHttpsConnection(String path, Method method) throws Exception {
		final URL url = new URL(path);
		final HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod(method.getName());
		conn.setReadTimeout(5000);
		return conn;
	}

	private static void setZhidao1(HttpURLConnection conn) {
		conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
		conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		conn.setRequestProperty("Referer","https://www.baidu.com/link?url=lSk2d3ZS978NBWsbY30svn9rQPYT1TiBnU_YMebfF-plkmGLJoMfbog9mrq9escYL3h5y-lpnDTCNfn8BMhmK8r3wyT1RfmLFFFuY25jR4W&wd=&eqid=9ca2aab2000034a80000000259f2b818");
		conn.setRequestProperty("Accept-Encoding","gzip, deflate");
		conn.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
	}

	private static void printInputStream(URLConnection connection) throws Exception {
		final InputStream stream = connection.getInputStream();
		byte[] cache = new byte[1024];
		int len;
		while((len = stream.read(cache)) > 0) {
			System.out.println(new String(cache,0,len));
		}
	}
}
