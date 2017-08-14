package com.yc.weixin.web.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yc.weixin.bean.Menu;
import com.yc.weixin.biz.MenuBiz;
import com.yc.weixin.model.JsonModel;

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
		Integer sb_bid=Integer.valueOf(request.getParameter("onegradeselect"));
		menu.setBid(sb_bid);
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
	System.out.println(list);
	session.setAttribute("OneGradeMenu", list);
	
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
	public  String doAddMenu(HttpServletRequest request,Menu menu){
		JsonModel jm=new JsonModel();
		String menugrade=request.getParameter("menugrade").trim();
		System.out.println(menugrade);
		Integer sb_bid=Integer.valueOf(request.getParameter("onegradeselect"));
		menu.setBid(sb_bid);
		try {
			if(menugrade.equals("一级菜单")){
				menuBiz.AddOnemenu(menu);
			}else if(menugrade.equals("二级菜单")){
			 menuBiz.AddTwomenu(menu);
			 }
			jm.setCode(1);
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
	
	

}
