package com.yc.weixin.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yc.weixin.bean.Menu;
import com.yc.weixin.bean.TwoMenu;
import com.yc.weixin.biz.MenuBiz;
import com.yc.weixin.model.ButtonModel;
import com.yc.weixin.model.ErrorModel;
import com.yc.weixin.model.MenuModel;

/**
 * 自定义菜单工具类
 * 
 * @author 
 * @date 2013-10-17
 */
public class MenuUtil {
	
	private static MenuBiz menuBiz;

	
	public  MenuBiz getMenuBiz() {
	
		return menuBiz;
	}

	public  void setMenuBiz(MenuBiz menuBiz) {
		MenuUtil.menuBiz = menuBiz;
	}
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
	public static boolean createMenu( String accessToken) throws IOException {
		boolean result = false;
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		MenuModel menu=docreatemenu();
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
System.out.println(result);
System.out.println(jsonObject);
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
	public static  MenuModel  docreatemenu(){

		MenuModel mm = new MenuModel();
	//MenuBiz menuBiz=	(MenuBiz) ac.getBean("menuBizImpl");
		List<Menu> Onelist=new ArrayList<Menu>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", "aaa");
		Onelist=menuBiz.findAllOneMenu(map);
		List<ButtonModel> list1 = new ArrayList<ButtonModel>();//一级菜单 
	
		for(Menu me:Onelist){
			List<ButtonModel> list2 = new ArrayList<ButtonModel>();//单个一级菜单里的子菜单
			ButtonModel firstbutton = new ButtonModel();//一级菜单model
			
			firstbutton.setName(me.getName());
			if(null!=me.getMenutype()&&me.getMenutype().equals("click")){//遍历一个一级菜单出来就把他存ButtonModel
				firstbutton.setType(me.getMenutype());
				firstbutton.setKey(me.getMenukey());
			}else if(null!=me.getMenutype()&&me.getMenutype().equals("view")){
				firstbutton.setType(me.getMenutype());
				firstbutton.setUrl(me.getUrl());
			}
			
			Map<String,Integer> OneBidmap=new HashMap<String,Integer>();
			OneBidmap.put("bid",me.getBid());
			
			List<TwoMenu> TwoList=menuBiz.findTwoMenuByOneName(OneBidmap);
			if(TwoList.size()>0){
			for(TwoMenu tm:TwoList){
				ButtonModel secondbutton = new ButtonModel();
				secondbutton.setName(tm.getName());
				if(tm.getMenutype().equals("click")){
					secondbutton.setType(tm.getMenutype());
					secondbutton.setKey(tm.getMenukey());
				}else if(tm.getMenutype().equals("view")){
					secondbutton.setType(tm.getMenutype());
					secondbutton.setUrl(tm.getUrl());
				}
				
				list2.add(secondbutton);
			
			}
			
			firstbutton.setSub_button(list2);//把一个一级子菜单里面的5个二级菜单存进去
			}
			list1.add(firstbutton);//  5   10   15
			
		}
			mm.setButton(list1);
			return mm;
	}
}