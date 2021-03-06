package com.yc.weixin.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import com.yc.weixin.model.UserGroupModel;
import com.yc.weixin.model.UserModel;

public class UserInfoUtil {

	
	private static String userInfouri = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	private static String openIduri = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
	/**
	 * 获取用户信息
	 * @param openid	
	 * @param access_token
	 * @return
	 * @throws IOException
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static List<UserModel> getAllUserInfo(String access_token) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		List<String> openid = getAllOpenId(access_token);
		String response = "";
		List<UserModel> list = new ArrayList<UserModel>();
		for (String id : openid) {
			userInfouri = userInfouri.replace("ACCESS_TOKEN", access_token);
			userInfouri = userInfouri.replace("OPENID", id);
			response = CommonUtil.getResources(userInfouri,null);
			UserModel um = CommonUtil.gson.fromJson(response, UserModel.class);
			System.out.println(um);
			list.add(um);
		}
		return list;
	}
	
	//获取单个用户的用户信息
	public static UserModel getUserInfo(String access_token,String openid) throws IOException{
		String response = "";
		UserModel um = new UserModel();
		userInfouri = userInfouri.replace("ACCESS_TOKEN", access_token);
		userInfouri = userInfouri.replace("OPENID", openid);
		response = CommonUtil.getResources(userInfouri,null);
		um = CommonUtil.gson.fromJson(response, UserModel.class);
		return um;
	}

	//获取用户头像的url
	public static List<String> getUserHeadImgUrl() throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		String access_token = AccessTokenUtil.access_token;
		List<UserModel> users = getAllUserInfo(access_token);
		List<String> headurl = new ArrayList<String>();
		for(UserModel um:users){
			headurl.add(um.getHeadimgurl());
		}
		return headurl;
	}
	
	/**
	 * 
	 * @param accessToken	微信提供的标识，需要通过拿到此标识才能够让微信端提供服务,每个token的有效期为两个小时
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	//获取关注用户列表的openid,openid对于当前关注此微信号的用户来说是唯一的,一次最多能拿到10000个
	//通过返回的next_openid可以继续往下获取
	private static List<String> getAllOpenId(String accessToken)
			throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		openIduri = openIduri.replace("ACCESS_TOKEN", accessToken);
		//自定义信任管理器，完成https链接
		// TrustManager[] tm = {new My509TrustManager()};
		// SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		// sslContext.init(null, tm, new java.security.SecureRandom());
		// SSLSocketFactory ssf = sslContext.getSocketFactory();

		String response = CommonUtil.getResources(openIduri,null);
		UserGroupModel ugm = CommonUtil.gson.fromJson(response, UserGroupModel.class);

		for (String openid : ugm.getData().getOpenid()) {
			System.out.println(openid);
		}

		return ugm.getData().getOpenid();
	}


}
