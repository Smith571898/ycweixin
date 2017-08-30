package com.yc.weixin.web.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yc.weixin.bean.ArticleMaterial;
import com.yc.weixin.bean.Material;
import com.yc.weixin.bean.NewsMaterial;
import com.yc.weixin.biz.NewsBiz;
import com.yc.weixin.model.ArticleMaterialModel;
import com.yc.weixin.model.JsonModel;
import com.yc.weixin.model.MediaModel;
import com.yc.weixin.model.NewsMaterialModel;
import com.yc.weixin.utils.CommonUtil;
import com.yc.weixin.utils.FileLoadUtil;
import com.yc.weixin.utils.MediaUtil;
import com.yc.weixin.utils.SendMateriaMessage;
import com.yc.weixin.utils.UpdatePicToQPic;

@RestController
public class NewsController {

	@Resource(name = "newsBizImpl")
	private NewsBiz newsBiz;
	
	// 多图文消息的处理
	@RequestMapping(path = "addArticle.action")
	public void addArticle(@RequestParam(name = "file", required = false) MultipartFile file,
			ArticleMaterialModel article, HttpServletRequest req) {
		if (req.getParameter("head") != null) {
			CommonUtil.news.clear();
		}

		if (!file.isEmpty()) {
			try {
				// 获取封面永久缩略图id
				String filepath = FileLoadUtil.upload(file, req, "material");
				MediaModel mm = MediaUtil.uploadMateria("image", filepath);
				article.setThumb_media_id(mm.getMedia_id());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {

		}

		// 更换图文内容中的图片地址为微信服务器所提供的地址
		article.setContent(UpdatePicToQPic.update(article.getContent()));

		CommonUtil.news.add(article);

		if (req.getParameter("tail") != null) {
			NewsMaterialModel nmm = new NewsMaterialModel();
			nmm.setArticles(CommonUtil.news);
			System.out.println("图文消息接收完毕");
			// 上传多图文消息素材
			MediaModel mediaModel = MediaUtil.uploadNewsMateria(nmm);
			System.out.println("------" + mediaModel);
			NewsMaterial newsMaterial = new NewsMaterial();
			List<ArticleMaterial> list = new ArrayList<ArticleMaterial>();
			newsMaterial.setCreate_at(String.valueOf(System.currentTimeMillis()));
			newsMaterial.setMedia_id(mediaModel.getMedia_id());
			newsMaterial.setType("news");
			newsMaterial.setStatus(0);

			int index = 0;
			for (ArticleMaterialModel amm : nmm.getArticles()) {
				ArticleMaterial articleMaterial = new ArticleMaterial();
				articleMaterial.setAuthor(amm.getAuthor());
				articleMaterial.setContent(amm.getContent());
				articleMaterial.setContent_source_url(amm.getContent_source_url());
				articleMaterial.setDigest(amm.getDigest());
				articleMaterial.setMedia_id(mediaModel.getMedia_id());
				articleMaterial.setShow_cover_pic(amm.getShow_cover_pic());
				articleMaterial.setStatus(index);
				articleMaterial.setThumb_media_id(amm.getThumb_media_id());
				articleMaterial.setTitle(amm.getTitle());
				index++;
				list.add(articleMaterial);
			}
			newsBiz.addNews(newsMaterial, list);

			SendMateriaMessage.sendMateriaMessageByTag(mediaModel);
		}
	}
	
	// 查询多图文
	@RequestMapping(path="toUpdateNews.action")
	public String toupdateNews(@RequestParam(name="media_id")String mediaId){
		JsonModel jsonModel = new JsonModel();
		try {
			List<ArticleMaterial> list = newsBiz.findArticles(mediaId);
			jsonModel.setCode(1);
			jsonModel.setObj(list);
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
	
	@RequestMapping(path="findNews.action")
	public String findNewsMaterial(HttpServletRequest request){
		JsonModel jsonModel = new JsonModel();
		int pages = Integer.parseInt(request.getParameter("page").toString());
		int pagesize = Integer.parseInt(request.getParameter("rows").toString());
		int start = (pages - 1) * pagesize;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("pagesize", pagesize);
		List<NewsMaterial> list = newsBiz.findNews(map);
		Integer total = newsBiz.finNewsCount();
		jsonModel.setRows(list);
		jsonModel.setTotal(total);
		Gson gson = new Gson();
		Type jsonType = new TypeToken<JsonModel>() {
		}.getType();
		String jsonStr = gson.toJson(jsonModel, jsonType);
		return jsonStr;
	}
}
