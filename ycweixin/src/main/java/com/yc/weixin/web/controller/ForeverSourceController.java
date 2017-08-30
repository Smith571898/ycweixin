package com.yc.weixin.web.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yc.weixin.bean.FollowPushMessage;
import com.yc.weixin.bean.Material;
import com.yc.weixin.biz.MaterialBiz;
import com.yc.weixin.model.JsonModel;
import com.yc.weixin.model.MediaModel;
import com.yc.weixin.utils.FileLoadUtil;
import com.yc.weixin.utils.MediaUtil;

@RestController
public class ForeverSourceController {

	@Resource(name = "materialBizImpl")
	private MaterialBiz materialBiz;

	@RequestMapping(value = "douploadForeverpicbat", method = RequestMethod.POST)
	public void Sourceupload(@RequestParam(value = "fpics", required = false) MultipartFile[] file,
			HttpServletRequest request, HttpServletResponse resp) throws IOException {

		List<String> listImagePath = new ArrayList<String>();
		for (MultipartFile mf : file) {
			try {
				String fileurl = FileLoadUtil.upload(mf, request, "source");// E:\apache-tomcat-8.0.44\webapps\source\b8dbf687fcb4435cafd63e7d2145d358.png
				MediaModel mediaModel = new MediaModel();
				mediaModel = MediaUtil.uploadMateria("image", fileurl);
				Material material = new Material();
				material.setMedia_id(mediaModel.getMedia_id());
				material.setMpurl(mediaModel.getUrl());
				material.setUrl(fileurl.substring(fileurl.lastIndexOf(File.separator) + 1));
				material.setType("image");
				material.setCreate_at(System.currentTimeMillis());
				materialBiz.addMaterial(material);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@RequestMapping(value = "douploadForeverVideobat.action", method = RequestMethod.POST)
	public void Sourceupload1(@RequestParam(value = "fvideos", required = false) MultipartFile[] file,
			HttpServletRequest request, HttpServletResponse resp) throws IOException {

		List<String> listImagePath = new ArrayList<String>();
		for (MultipartFile mf : file) {
			try {
				String fileurl = FileLoadUtil.upload(mf, request, "video");// E:\apache-tomcat-8.0.44\webapps\source\b8dbf687fcb4435cafd63e7d2145d358.png
				MediaModel mediaModel = new MediaModel();
				mediaModel = MediaUtil.uploadMateria("video", fileurl);
				Material material = new Material();
				material.setMedia_id(mediaModel.getMedia_id());
				material.setMpurl(mediaModel.getUrl());
				material.setUrl(fileurl.substring(fileurl.lastIndexOf(File.separator) + 1));
				material.setType("video");
				material.setCreate_at(System.currentTimeMillis());
				materialBiz.addMaterial(material);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@RequestMapping(value = "douploadForeverAudiobat.action", method = RequestMethod.POST)
	public void Sourceupload2(@RequestParam(value = "faudios", required = false) MultipartFile[] file,
			HttpServletRequest request, HttpServletResponse resp) throws IOException {

		List<String> listImagePath = new ArrayList<String>();
		for (MultipartFile mf : file) {
			try {
				String fileurl = FileLoadUtil.upload(mf, request, "voice");// E:\apache-tomcat-8.0.44\webapps\source\b8dbf687fcb4435cafd63e7d2145d358.png
				MediaModel mediaModel = new MediaModel();
				mediaModel = MediaUtil.uploadMateria("voice", fileurl);
				Material material = new Material();
				material.setMedia_id(mediaModel.getMedia_id());
				material.setMpurl(mediaModel.getUrl());
				material.setUrl(fileurl.substring(fileurl.lastIndexOf(File.separator) + 1));
				material.setType("voice");
				material.setCreate_at(System.currentTimeMillis());
				materialBiz.addMaterial(material);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@RequestMapping(value = "findMaterial.action", produces = "text/html;charset=UTF-8")
	public String findAllUserInfo(HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();
		int pages = Integer.parseInt(request.getParameter("page").toString());
		int pagesize = Integer.parseInt(request.getParameter("rows").toString());
		int start = (pages - 1) * pagesize;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("pagesize", pagesize);
		List<Material> list = materialBiz.findMaterial(map);
		Integer total = materialBiz.finMaterialCount();
		jsonModel.setRows(list);
		jsonModel.setTotal(total);
		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}

	@RequestMapping(value = "dodelMaterial.action", produces = "text/html;charset=UTF-8")
	public String delForeverMaterial(Material material, HttpServletRequest request) {
		JsonModel jsonModel = new JsonModel();
		MediaModel mediaModel = new MediaModel();
		mediaModel.setMedia_id(material.getMedia_id());
		try {
			MediaUtil.delMaterial(mediaModel);
			materialBiz.delMaterial(material);
			jsonModel.setCode(1);
		} catch (Exception e) {
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
			e.printStackTrace();
		}
		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}
}
