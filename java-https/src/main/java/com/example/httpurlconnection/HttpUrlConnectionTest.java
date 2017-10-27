package com.example.httpurlconnection;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import javax.net.ssl.HttpsURLConnection;


public class HttpUrlConnectionTest{
	private static final String ZHIDAO_1 = "http://www.baidu.com//link?url=lSk2d3ZS978NBWsbY30svn9rQPYT1TiBnU_YMebfF-plkmGLJoMfbog9mrq9escYL3h5y-";
	private static final String TRANSLATE_MARKDOWN = "https://api.github.com/markdown/raw";

	public static void main(String[] args) throws Exception {
		System.out.println(translateMarkDown());
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
		String x = URLDecoder.decode("https://zhidao.baidu.com/question/166627039.html?qbl=relate_question_0&word=%CB%F9%D3%D0%B5%C4%B8%DF%BC%B6%D3%EF%D1%D4%20C%D3%EF%D1%D4");
		x = new String(x.getBytes(),"gbk");
		System.out.println(x);
		final HttpURLConnection connection = buildConnection(ZHIDAO_1);
		setZhidao1(connection);
		connection.connect();
		final InputStream stream = connection.getInputStream();
		byte[] cache = new byte[1024];
		int len;
		while((len = stream.read(cache)) > 0) {
			System.out.println(new String(cache,0,len,"ISO8859-1"));
		}
	}

	private static HttpURLConnection buildConnection(String path) throws Exception {
		final URL url = new URL(path);
		final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(10000);
		conn.setRequestMethod("POST");
		conn.setReadTimeout(10000);
		return conn;
	}

	private static HttpsURLConnection buildHttpsConnection(String path) throws Exception {
		final URL url = new URL(path);
		final HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
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
}
