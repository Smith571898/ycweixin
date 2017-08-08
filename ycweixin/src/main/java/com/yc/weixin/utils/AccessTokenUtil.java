package com.yc.weixin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class AccessTokenUtil {

	public static final String APPID = "wx72a20d360dea4bbb";

	public static final String APPSECRET = "696912240df8aa8577579e5391a26574";

	public static String getAccessToken() throws IOException {
		String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret="
				+ APPSECRET;
		URL url = new URL(uri);
		URLConnection connUrl = url.openConnection();
		InputStreamReader is = new InputStreamReader(connUrl.getInputStream(), "utf-8");

		System.out.println(connUrl.getRequestProperty("User-Agent"));

		char[] buf = new char[1024];
		int length = 0;
		String response = "";
		while ((length = is.read(buf, 0, buf.length)) != -1) {
			response = new String(buf, 0, length);
			System.out.println(response);
		}

		is.close();
		return response;
	}

	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		String accessToken = "vaLS9R8MObA6WnJ7TJMxw9WD4Rfels5f8trUJSprgoo6VNJmkpP2MrIKU-rSLI6S_pzKFnCk-fonviTK6I5rwzGuTzu8pzlAKzmU7pZyvxcEEXhAHAMMF";
		String response = getAllOpenId(accessToken);
	}

	public static String getAllOpenId(String accessToken) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		String uri = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;
//		TrustManager[] tm = {new My509TrustManager()};
//		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//		sslContext.init(null, tm, new java.security.SecureRandom());
//		SSLSocketFactory ssf = sslContext.getSocketFactory();
		
		URL url = new URL(uri);
		URLConnection connUrl =  url.openConnection();
		InputStreamReader is = new InputStreamReader(connUrl.getInputStream(), "utf-8");

		System.out.println(connUrl.getRequestProperty("User-Agent"));

		char[] buf = new char[1024];
		int length = 0;
		String response = "";
		while ((length = is.read(buf, 0, buf.length)) != -1) {
			response = new String(buf, 0, length);
			System.out.println(response);
		}

		is.close();
		return response;
	}
}
