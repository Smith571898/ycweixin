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

	public static final String APPID = "wx72a20d360dea4bbb";

	public static final String APPSECRET = "696912240df8aa8577579e5391a26574";

	//获取access_token，拿到token后可以访问微信提供的接口
	public static String getAccessToken() throws IOException {
		String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret="
				+ APPSECRET;

		String response = CommonUtil.getResources(uri);

		AcessTokenModel atm = CommonUtil.gson.fromJson(response, AcessTokenModel.class);

		System.out.println(atm);

		return atm.getAccess_token();
	}


}
