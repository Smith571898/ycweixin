package com.yc.weixin.utils;

import java.io.IOException;

import com.yc.weixin.model.Oauth2TokenModel;
import com.yc.weixin.model.SNSUserInfoModel;

//网页授权
public class OAuthPowerUtil {

	//获取code
	private static final String getCodeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	//获取网页授权token
	private static final String getOauthTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	//获取用户信息
	private static final String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	//生成获取code的页面
	public static String getCode(String redirectUrl,String scope) {
		String getCodeUrl1 = getCodeUrl;
		getCodeUrl1 = getCodeUrl1.replace("APPID", AccessTokenUtil.APPID);
		getCodeUrl1 = getCodeUrl1.replace("REDIRECT_URI", redirectUrl);
		getCodeUrl1 = getCodeUrl1.replace("SCOPE", scope);
		getCodeUrl1 = getCodeUrl1.replace("STATE", String.valueOf(123));
		return getCodeUrl1;
	}
	
	//通过code获得页面授权token以及用户openid
	public static Oauth2TokenModel getOauthToken(String code){
		Oauth2TokenModel token = null;
		
		String getOauthTokenUrl1 = getOauthTokenUrl;
		getOauthTokenUrl1 = getOauthTokenUrl1.replace("APPID", AccessTokenUtil.APPID);
		getOauthTokenUrl1 = getOauthTokenUrl1.replace("SECRET", AccessTokenUtil.APPSECRET);
		getOauthTokenUrl1 = getOauthTokenUrl1.replace("CODE", code);
		
		try {
			String response = CommonUtil.getResources(getOauthTokenUrl1, null);
			token = CommonUtil.gson.fromJson(response, Oauth2TokenModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return token;
	}

	//通过token以及用户openid获取用户信息
	public static SNSUserInfoModel getUserInfo(Oauth2TokenModel token){
		SNSUserInfoModel snsUserInfo = null;
		
		String getUserInfoUrl1 = getUserInfoUrl;
		getUserInfoUrl1 = getUserInfoUrl1.replace("ACCESS_TOKEN", token.getAccessToken());
		getUserInfoUrl1 = getUserInfoUrl1.replace("OPENID", token.getOpenId());
		
		try {
			String response = CommonUtil.getResources(getUserInfoUrl1, null);
			snsUserInfo = CommonUtil.gson.fromJson(response, Oauth2TokenModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return snsUserInfo;
	}
	
}
