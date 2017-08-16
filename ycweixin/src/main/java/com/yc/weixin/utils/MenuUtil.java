package com.yc.weixin.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yc.weixin.model.ErrorModel;
import com.yc.weixin.model.MenuModel;

/**
 * 自定义菜单工具类
 * 
 * @author 
 * @date 2013-10-17
 */
public class MenuUtil {
	private static Logger log = LoggerFactory.getLogger(MenuUtil.class);

	// 菜单创建（POST）
	private final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 菜单查询（GET）
	private final static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除（GET）
	private final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 凭证
	 * @return true成功 false失败
	 * @throws IOException 
	 */
	public static boolean createMenu(MenuModel menu, String accessToken) throws IOException {
		boolean result = false;
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = CommonUtil.gson.toJson(menu);
		// 发起POST请求创建菜单
		String jsonObject = CommonUtil.getResources(url, jsonMenu);
		ErrorModel em = CommonUtil.gson.fromJson(jsonObject, ErrorModel.class);

		if (null != em) {
			int errorCode = em.getErrcode();
			String errorMsg = em.getErrmsg();
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("创建菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}

		return result;
	}

	/**
	 * 查询菜单
	 * 
	 * @param accessToken 凭证
	 * @return
	 * @throws IOException 
	 */
	public static MenuModel getMenu(String accessToken) throws IOException {
		String result = null;
		String requestUrl = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求查询菜单
		String jsonObject = CommonUtil.getResources(requestUrl, null);

		if (null != jsonObject) {
			result = jsonObject.toString();
		}
		MenuModel mm = CommonUtil.gson.fromJson(result, MenuModel.class);
		return mm;
	}

	/**
	 * 删除菜单
	 * 
	 * @param accessToken 凭证
	 * @return true成功 false失败
	 * @throws IOException 
	 */
	public static boolean deleteMenu(String accessToken) throws IOException {
		boolean result = false;
		String requestUrl = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求删除菜单
		String jsonObject = CommonUtil.getResources(requestUrl, null);
		ErrorModel em = CommonUtil.gson.fromJson(jsonObject, ErrorModel.class);

		if (null != em) {
			int errorCode = em.getErrcode();
			String errorMsg = em.getErrmsg();
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("删除菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
}