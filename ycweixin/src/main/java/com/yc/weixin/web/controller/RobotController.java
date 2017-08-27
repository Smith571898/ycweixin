package com.yc.weixin.web.controller;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yc.weixin.bean.Chat_log;
import com.yc.weixin.biz.ChatBiz;
import com.yc.weixin.model.JsonModel;

@RestController
public class RobotController {
	@Resource(name="chatBizImpl")
	private ChatBiz chatBiz;
	
	/**
	 * 用来加载所有
	 * @param request
	 * @return
	 */
	@RequestMapping(value="dofindChatLog.action",produces="text/html;charset=UTF-8")
	public String findChatLog(HttpServletRequest request){
		JsonModel jsonModel  =new JsonModel();
		int pages = Integer.parseInt(request.getParameter("page").toString());
		int pagesize = Integer.parseInt(request.getParameter("rows").toString());
		int start=(pages-1)*pagesize;
		String orderby=request.getParameter("sort");
		String  orderway=request.getParameter("order");
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", start);
		map.put("pagesize", pagesize);
		
		map.put("orderby", orderby);
		map.put("orderway", orderway);
		List<Chat_log> list =chatBiz.findAllChat_Log(map);
		Integer total=chatBiz.getChatLogCount();
		
		jsonModel.setRows(list);
		jsonModel.setTotal(total);
		
		Gson gson =new Gson();
		Type jsonType=new TypeToken<JsonModel>(){			
		}.getType();
		String jsonStr=gson.toJson(jsonModel, jsonType);
		return jsonStr;			
	}
	
	@RequestMapping(value="dofindChatLogByDate.action",produces="text/html;charset=UTF-8")
	public String findChatLogByDate(@RequestParam(name="createtime") String createtime,HttpServletRequest request){
		JsonModel jsonModel  =new JsonModel();
		Date date = new Date(createtime);
				long starttime=(date.getTime()/1000);
				long endtime=starttime+86400;

		
		int pages = Integer.parseInt(request.getParameter("page").toString());
		int pagesize = Integer.parseInt(request.getParameter("rows").toString());
		int start=(pages-1)*pagesize;
		String orderby=request.getParameter("sort");
		String  orderway=request.getParameter("order");
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", start);
		map.put("pagesize", pagesize);
		
		map.put("orderby", orderby);
		map.put("orderway", orderway);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		List<Chat_log> list =chatBiz.findChatLogByDate(map);
		Integer total=chatBiz.getChatLogCountByDate(map);
		
		jsonModel.setRows(list);
		jsonModel.setTotal(total);
		
		Gson gson =new Gson();
		Type jsonType=new TypeToken<JsonModel>(){			
		}.getType();
		String jsonStr=gson.toJson(jsonModel, jsonType);
		return jsonStr;			
	}
}
