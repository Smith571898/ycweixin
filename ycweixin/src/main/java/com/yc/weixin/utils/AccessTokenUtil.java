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

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yc.weixin.model.AcessTokenModel;
import com.yc.weixin.model.UserGroupModel;
import com.yc.weixin.model.UserModel;

@Component
public class AccessTokenUtil {

	public static final String APPID = "wxe193c3a51e0481e7";

	public static final String APPSECRET = "42ca5b1df322bcb96515ff364826bbd8";
	
	public static String access_token = getAccessToken();

	//获取access_token，拿到token后可以访问微信提供的接口
	@Scheduled(cron="0 0 */2 * * ? ")
	private static String getAccessToken() {
		try {
			String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret="
					+ APPSECRET;

			String response = CommonUtil.getResources(uri,null);

			AcessTokenModel atm = CommonUtil.gson.fromJson(response, AcessTokenModel.class);

			access_token = atm.getAccess_token();
			
			System.out.println(access_token);
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		return access_token;
	}
}
