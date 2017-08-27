package com.yc.weixin.web.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yc.weixin.bean.WeChatUser;
import com.yc.weixin.biz.UserBiz;
import com.yc.weixin.model.JsonModel;







@RestController
public class WeChatUserInfoController {
	@Resource(name="userBizImpl")
	private  UserBiz userBiz;
	
	@RequestMapping(value="WeChatUserInfo.action")
	public  ModelAndView  ShowUserInfo(){
		ModelAndView mav =new ModelAndView();
		
		mav.setViewName("WeChatUserInfo");
		return mav;
	}
	
	@RequestMapping(value="findAllUserInfo.action",produces="text/html;charset=UTF-8")
	public String findAllUserInfo(HttpServletRequest request){
		JsonModel jsonModel  =new JsonModel();
		int pages = Integer.parseInt(request.getParameter("page").toString());
		int pagesize = Integer.parseInt(request.getParameter("rows").toString());
		int start=(pages-1)*pagesize;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", start);
		map.put("pagesize", pagesize);
		List<WeChatUser> list = userBiz.findAllUser(map);
		Integer total=userBiz.findWeChatUserCount();
		jsonModel.setRows(list);
		jsonModel.setTotal(total);
		
		Gson  gson=new Gson();
		Type jsonType=new TypeToken<JsonModel>(){			
		}.getType();
		String jsonStr=gson.toJson(jsonModel, jsonType);
		
		System.out.println(jsonStr);
		return jsonStr;
	}
}
