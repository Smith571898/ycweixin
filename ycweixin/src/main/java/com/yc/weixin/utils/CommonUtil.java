package com.yc.weixin.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class CommonUtil {

	public static Gson gson = new Gson();
	
	//通过url与微信端建立链接,得到微信端响应的信息
	public static String getResources(String uri) throws IOException {
		URL url = new URL(uri);
		URLConnection connUrl = url.openConnection();
		InputStreamReader is = new InputStreamReader(connUrl.getInputStream(), "utf-8");

		char[] buf = new char[10 * 1024];
		int length = 0;
		String response = "";
		while ((length = is.read(buf, 0, buf.length)) != -1) {
			response = new String(buf, 0, length);
		}
		is.close();

		return response;
	}
}
