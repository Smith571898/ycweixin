package com.yc.weixin.web.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yc.weixin.bean.ShowMenu;

import com.yc.weixin.bean.Menu;
import com.yc.weixin.bean.TwoMenu;
import com.yc.weixin.biz.MenuBiz;
import com.yc.weixin.model.JsonModel;
import com.yc.weixin.utils.AccessTokenUtil;
import com.yc.weixin.utils.MenuUtil;

@RestController
public class MenuController {
	@Resource(name="menuBizImpl")
	private MenuBiz menuBiz;
	/**
	 * 
	 * 查找二级菜单
	 * @param request
	 * @return
	 */
	
	@RequestMapping("ofonemenu.action")
	public  JsonModel Street_list(){
		List<Menu>  list =menuBiz.findAllOneMenu(null);
		JsonModel jm=new  JsonModel();
		jm.setRows(list);
		System.out.println(list);
		return jm;
		
		
	}
		
	@RequestMapping(value="findAllMenu.action",produces="text/html;charset=UTF-8")
	public  String  findTwoMenu(HttpServletRequest request){
	JsonModel jsonModel=new JsonModel();
	Map map=this.paging(request);
	List<Menu> list=menuBiz.findAllMenu(map);
	int total=menuBiz.getMenuCount();
	jsonModel.setRows(list);
	jsonModel.setTotal(total);
	Gson gson=new Gson();
	Type jsonType=new TypeToken<JsonModel>(){
		
	}.getType();
	String jsonStr=gson.toJson(jsonModel,jsonType);
		return jsonStr;
	}
	/**
	 * 
	 * 更新一级菜单
	 * @param request
	 * @param menu
	 * @return
	 */
	
	@RequestMapping(value="doupdateTwomenu.action",produces="text/html;charset=UTF-8")
	public  String  doupdatemenu(HttpServletRequest request,Menu menu){
		
		JsonModel jsonModel=new  JsonModel();
		try {
			menuBiz.updateMenu(menu);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		Gson gson=new Gson();
		Type jsonType=new TypeToken<JsonModel>(){
			
		}.getType();
		String jsonStr=gson.toJson(jsonModel,jsonType);
		return jsonStr;
		
	}
	/**
	 * 查找一级菜单
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="findOneMenu.action",produces="text/html;charset=UTF-8")
	public  String findOneMenu(HttpServletRequest request,HttpSession session){
	
	JsonModel jsonModel=new JsonModel();
	Map map=this.paging(request);
	List<Menu> list=menuBiz.findAllOneMenu(map);
	
	int total=menuBiz.getMenuOneCount();
	jsonModel.setRows(list);
	jsonModel.setTotal(total);
	Gson gson=new Gson();
	Type jsonType=new TypeToken<JsonModel>(){
		
	}.getType();
	String jsonStr=gson.toJson(jsonModel,jsonType);
		return jsonStr;
	}
	
/**
 * 添加二级菜单
 * @param request
 * @return
 */
	@RequestMapping(value="doAddMenu.action",produces="text/html;charset=UTF-8")
	public  String doAddMenu(@RequestParam(name="onegradeselect1",required=false) Integer sb_bid,HttpServletRequest request,Menu menu){
		JsonModel jm=new JsonModel();
		String menugrade=request.getParameter("menugrade").trim();
		System.out.println(menugrade);
		menu.setBid(sb_bid);
		try {
			if(menugrade.equals("一级菜单")){
				
				menuBiz.AddOnemenu(menu);
				jm.setCode(1);
			}else if(menugrade.equals("二级菜单")){
				Map<String,Object>  map=new HashMap<String,Object>();
				map.put("bid", sb_bid);
			List<TwoMenu> list=	menuBiz.findTwoMenuByOneName(map);
				if(list.size()>=5){
					jm.setCode(2);
					jm.setMsg("该一级菜单的二级菜单已经达到5个上限,请删除或者修改菜单");
				}else{
				menuBiz.AddTwomenu(menu);
				jm.setCode(1);
				}
			 
			 }
		
		} catch (Exception e) {			
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		Gson gson=new Gson();
		Type jsonType=new TypeToken<JsonModel>(){
			
		}.getType();
		String jsonStr=gson.toJson(jm,jsonType);
			return jsonStr;
		}
	
	//TODO:	删除一级 二级菜单
	
	@RequestMapping(value="dodeletemenu.action",produces="text/html;charset=UTF-8")
	public  String doDeleteMenu(@RequestParam(name="sbid",required=false) Integer sbid,@RequestParam(name="bid",required=false) Integer bid,HttpServletRequest request){
		JsonModel jm=new JsonModel();
		String menugrade=request.getParameter("menugrade").trim();
		Menu menu =new Menu();
		try {
			if(menugrade.equals("一级菜单")){
				Map<String,Object>  map=new HashMap<String,Object>();
				map.put("bid", bid);
				List<TwoMenu> list=	menuBiz.findTwoMenuByOneName(map);
				if(list.size()>0){
					jm.setCode(2);
					jm.setMsg("该一级菜单里面包含二级菜单，请先删除二级菜单");
					}else{
						menu.setBid(bid);
						menuBiz.deleteOneMenu(menu);
						jm.setCode(1);
					}
				
			}else if(menugrade.equals("二级菜单")){	
				
				menu.setSbid(sbid);
				menuBiz.deleteTwoMenu(menu);
				jm.setCode(1);
			 }
			
		} catch (Exception e) {			
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		Gson gson=new Gson();
		Type jsonType=new TypeToken<JsonModel>(){
			
		}.getType();
		String jsonStr=gson.toJson(jm,jsonType);
			return jsonStr;
		}
	/**
	 * 
	 * 更新一级菜单
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="doupdateOnemenu.action",produces="text/html;charset=UTF-8")
	public  String  doupdateOnemenu(HttpServletRequest request,Menu menu){		
		JsonModel jsonModel=new  JsonModel();
	
		try {
			menuBiz.updateOneMenu(menu);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		Gson gson=new Gson();
		Type jsonType=new TypeToken<JsonModel>(){
			
		}.getType();
		String jsonStr=gson.toJson(jsonModel,jsonType);
		return jsonStr;
		
	}
	/**
	 * 分页包装
	 * @param request
	 * @return
	 */
	public Map paging(HttpServletRequest request){
		int pages = Integer.parseInt(request.getParameter("page").toString());
		int pagesize = Integer.parseInt(request.getParameter("rows").toString());
		int start=(pages-1)*pagesize;
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("start", start);
		map.put("pagesize", pagesize);
		return map;
		
	}

	
	@RequestMapping(value="getonecount.action",produces="text/html;charset=UTF-8")
		public String  getoneCount(){
		JsonModel jsonModel=new  JsonModel();
		try {
			int total=menuBiz.getMenuOneCount();
			jsonModel.setCode(1);
			jsonModel.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		Gson gson=new Gson();
		Type jsonType=new TypeToken<JsonModel>(){
			
		}.getType();
		String jsonStr=gson.toJson(jsonModel,jsonType);
		return jsonStr;
		
		
	}
		
	@RequestMapping(value="showMenuForeground.action",produces="text/html;charset=UTF-8")
	public  String  ShowMenuForeground(HttpServletRequest request,Menu menu){		
		JsonModel jsonModel=new  JsonModel();
		
		try {
			MenuUtil.deleteMenu(AccessTokenUtil.access_token);
			MenuUtil.createMenu(AccessTokenUtil.access_token);
			jsonModel.setCode(1);
		} catch (IOException e) {
		
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		Gson gson=new Gson();
		Type jsonType=new TypeToken<JsonModel>(){
			
		}.getType();
		String jsonStr=gson.toJson(jsonModel,jsonType);
		return jsonStr;
		
		}
		
	
	
	
	@RequestMapping(value="doundermenu.action",produces="text/html;charset=UTF-8")
	public  String  UnderMenuForeground(HttpServletRequest request,Menu menu){		
		JsonModel jsonModel=new  JsonModel();
		
		try {
			MenuUtil.deleteMenu(AccessTokenUtil.access_token);
			jsonModel.setCode(1);
		} catch (IOException e) {
		
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		Gson gson=new Gson();
		Type jsonType=new TypeToken<JsonModel>(){
			
		}.getType();
		String jsonStr=gson.toJson(jsonModel,jsonType);
		return jsonStr;
		
		}
	
	
}
