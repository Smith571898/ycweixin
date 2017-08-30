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
import com.yc.weixin.bean.Joke;
import com.yc.weixin.bean.Knowledge;
import com.yc.weixin.bean.Knowledge_sub;
import com.yc.weixin.biz.ChatBiz;
import com.yc.weixin.model.JsonModel;

@RestController
public class RobotController {
	@Resource(name = "chatBizImpl")
	private ChatBiz chatBiz;

	/**
	 * 用来加载所有
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dofindChatLog.action", produces = "text/html;charset=UTF-8")
	public String findChatLog(HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		Map<String, Object> map = page(request);
		
		List<Chat_log> list = chatBiz.findAllChat_Log(map);
		Integer total = chatBiz.getChatLogCount();

		jsonModel.setRows(list);
		jsonModel.setTotal(total);

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	@RequestMapping(value = "dofindChatLogByDate.action", produces = "text/html;charset=UTF-8")
	public String findChatLogByDate(@RequestParam(name = "createtime") String createtime, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();
		Date date = new Date(createtime);
		long starttime = (date.getTime() / 1000);
		long endtime = starttime + 86400;

		Map<String, Object> map = page(request);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		List<Chat_log> list = chatBiz.findChatLogByDate(map);
		Integer total = chatBiz.getChatLogCountByDate(map);

		jsonModel.setRows(list);
		jsonModel.setTotal(total);

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 
	 * 查找所有的一级知识子表的信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dofindknowledge.action", produces = "text/html;charset=UTF-8")
	public String findAllKnowledge(HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		Map<String, Object> map = page(request);

		List<Knowledge> list = chatBiz.findAllKnowLedge(map);
		Integer total = chatBiz.getKnowLedgeCount();

		jsonModel.setRows(list);
		jsonModel.setTotal(total);

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 
	 * 查找所有的一级知识子表的信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doupdateknowledge.action", produces = "text/html;charset=UTF-8")
	public String updateKnowledge(Knowledge knowledge, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		try {

			chatBiz.updateknowledge(knowledge);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	@RequestMapping(value = "doAddknowledge.action", produces = "text/html;charset=UTF-8")
	public String addKnowledge(Knowledge knowledge, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();
		System.out.println(knowledge.getId());

		try {
			chatBiz.addknowledge(knowledge);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	@RequestMapping(value = "dofindAllJoke.action", produces = "text/html;charset=UTF-8")
	public String findAllJoke(HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		Map<String, Object> map = page(request);
		List<Joke> list = chatBiz.findAllJoke(map);
		Integer total = chatBiz.getJokeCount();

		jsonModel.setRows(list);
		jsonModel.setTotal(total);

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 查看二级子表
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "dofindTwoknowledge.action", produces = "text/html;charset=UTF-8")
	public String findtwoknowledge(HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		Map<String, Object> map = page(request);
		List<Knowledge_sub> list = chatBiz.findAllTwoKnowLedge(map);
		Integer total = chatBiz.getTwoKnowledgeCount();

		jsonModel.setRows(list);
		jsonModel.setTotal(total);

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 
	 * 修改二级表
	 */

	@RequestMapping(value = "doupdatetwoknowledge.action", produces = "text/html;charset=UTF-8")
	public String updateTwoKnowledge(Knowledge_sub knowledge_sub, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		try {

			chatBiz.updatetwoknow(knowledge_sub);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 添加二级答复信息
	 * 
	 * @param knowledge_sub
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doAddTwoknowledge.action", produces = "text/html;charset=UTF-8")
	public String addTwoKnowledge(Knowledge_sub knowledge_sub, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		try {
			chatBiz.addtwoknow(knowledge_sub);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 
	 * 修改笑话信息
	 * 
	 * @param joke
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "doupdatejoke.action", produces = "text/html;charset=UTF-8")
	public String updateJoke(Joke joke, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		try {

			chatBiz.updatejoke(joke);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 增加笑话信息
	 * 
	 * @param joke
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doAddJoke.action", produces = "text/html;charset=UTF-8")
	public String addJoke(Joke joke, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		try {
			chatBiz.addjoke(joke);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 删除知识主表信息
	 * 
	 * @param joke
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dodelknow.action", produces = "text/html;charset=UTF-8")
	public String delknow(Knowledge knowledge, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		try {
			chatBiz.delknow(knowledge);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 删除笑话表信息
	 * 
	 * @param joke
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dodeljoke.action", produces = "text/html;charset=UTF-8")
	public String deljoke(Joke joke, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		try {
			chatBiz.deljoke(joke);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 删除笑话表信息
	 * 
	 * @param joke
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dodeltwoknow.action", produces = "text/html;charset=UTF-8")
	public String delTwoKnow(Knowledge_sub knowledge_sub, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();

		try {
			chatBiz.deltwoKnow(knowledge_sub);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}

		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	/**
	 * 分页函数
	 * 
	 * @param request
	 * @return
	 */
	public Map page(HttpServletRequest request) {
		int pages = Integer.parseInt(request.getParameter("page").toString());
		int pagesize = Integer.parseInt(request.getParameter("rows").toString());
		int start = (pages - 1) * pagesize;
		String orderby = request.getParameter("sort");
		String orderway = request.getParameter("order");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("pagesize", pagesize);

		map.put("orderby", orderby);
		map.put("orderway", orderway);
		return map;
	}

}
