package com.yc.weixin.web.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yc.weixin.bean.FollowPushMessage;
import com.yc.weixin.biz.MessageBiz;
import com.yc.weixin.model.JsonModel;


@RestController
public class MessageController {
	@Resource(name="messageBizImpl")
	private MessageBiz  messageBiz;
	
	@RequestMapping(value="FollowPush.action")
	public  ModelAndView  FollowPushPage(){
		ModelAndView mav =new ModelAndView();
		
		mav.setViewName("FollowPush");
		return mav;
	}
	
	@RequestMapping(value="findFollowPushMessage.action",produces="text/html;charset=UTF-8")
	public String findAllUserInfo(HttpServletRequest request){
		JsonModel jsonModel  =new JsonModel();
		int pages = Integer.parseInt(request.getParameter("page").toString());
		int pagesize = Integer.parseInt(request.getParameter("rows").toString());
		int start=(pages-1)*pagesize;
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("start", start);
		map.put("pagesize", pagesize);
		List<FollowPushMessage> list=messageBiz.findFollowPushMessage(map);
		Integer total=messageBiz.finFollowPushCount();
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
