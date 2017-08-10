package com.yc.weixin.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.yc.weixin.model.AcessTokenModel;
import com.yc.weixin.model.UserGroupModel;
import com.yc.weixin.model.UserModel;

public class AccessTokenUtil {

	private static Gson gson = new Gson();

	public static final String APPID = "wx72a20d360dea4bbb";

	public static final String APPSECRET = "696912240df8aa8577579e5391a26574";

	public static String access_token;

	public static void getAccessToken() throws IOException {
		String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret="
				+ APPSECRET;

		String response = getResources(uri);

		AcessTokenModel atm = gson.fromJson(response, AcessTokenModel.class);

		System.out.println(atm);

		access_token = atm.getAccess_token();
	}

	private static List<String> getAllOpenId(String accessToken)
			throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		String uri = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;
		// TrustManager[] tm = {new My509TrustManager()};
		// SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		// sslContext.init(null, tm, new java.security.SecureRandom());
		// SSLSocketFactory ssf = sslContext.getSocketFactory();

		String response = getResources(uri);
		UserGroupModel ugm = gson.fromJson(response, UserGroupModel.class);

		for (String openid : ugm.getData().getOpenid()) {
			System.out.println(openid);
		}

		return ugm.getData().getOpenid();
	}

	private static List<String> getUserInfo(List<String> openid) throws IOException {
		String uri = "";
		String response = "";
		List<UserModel> list = new ArrayList<UserModel>();
		List<String> headurl = new ArrayList<String>();
		for (String id : openid) {
			uri = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + access_token + "&openid=" + id
					+ "&lang=zh_CN";
			response = getResources(uri);
			UserModel um = gson.fromJson(response, UserModel.class);
			list.add(um);
		}
		for (UserModel um : list) {
			headurl.add(um.getHeadimgurl());
		}

		return headurl;
	}

	private static String getResources(String uri) throws IOException {
		URL url = new URL(uri);
		URLConnection connUrl = url.openConnection();
		InputStreamReader is = new InputStreamReader(connUrl.getInputStream(), "utf-8");

		char[] buf = new char[10 * 1024];
		int length = 0;
		String response = "";
		while ((length = is.read(buf, 0, buf.length)) != -1) {
			response = new String(buf, 0, length);
			System.out.println(response);
		}
		is.close();

		return response;
	}

	public static List<String> getUserHeadImgUrl() throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		getAccessToken();
		List<String> openid = getAllOpenId(access_token);
		return getUserInfo(openid);
	}
}
