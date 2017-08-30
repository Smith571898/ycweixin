package com.yc.weixin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yc.weixin.model.ErrorModel;
import com.yc.weixin.model.MediaModel;

import com.yc.weixin.model.NewsMaterialModel;

public class MediaUtil {
	// 新增临时素材
	private static final String uploadTempMediaUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	// 新增永久素材
	private static final String uploadMaterialUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

	// 新增永久图文素材
	private static final String upploadNewsMateriaUrl = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";

	// 上传图文素材中的图片
	private static final String uploadPicFromNewsMaterialUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

	// 删除永久素材
	private static final String delMaterialUrl = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
	
	// 修改永久图文素材
	private static final String updateNewsMaterialUrl = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
	
	// 上传临时素材
	public static MediaModel uploadTempMedia(String type, String mediaFileUrl) {
		MediaModel mediaModel = null;
		System.out.println(type);
		String uploadTempMediaUrl1 = uploadTempMediaUrl;
		uploadTempMediaUrl1 = uploadTempMediaUrl1.replace("TYPE", type);
		uploadTempMediaUrl1 = uploadTempMediaUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);

		try {
			String response = CommonUtil.getMediaResource(uploadTempMediaUrl1, mediaFileUrl);
			mediaModel = CommonUtil.gson.fromJson(response, MediaModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mediaModel;
	}

	// 上传永久素材
	public static MediaModel uploadMateria(String type, String mediaFileUrl) {
		MediaModel mediaModel = null;
		
		String uploadMaterialUrl1 = uploadMaterialUrl;
		uploadMaterialUrl1 = uploadMaterialUrl1.replace("TYPE", type);
		uploadMaterialUrl1 = uploadMaterialUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);
		try {
			String response = CommonUtil.getMediaResource(uploadMaterialUrl1, mediaFileUrl);
			mediaModel = CommonUtil.gson.fromJson(response, MediaModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mediaModel;
	}

	// 上传永久图文素材
	public static MediaModel uploadNewsMateria(NewsMaterialModel nmm) {
		MediaModel mediaModel = null;
		
		String upploadNewsMateriaUrl1 = upploadNewsMateriaUrl;
		upploadNewsMateriaUrl1 = upploadNewsMateriaUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);

		String jsonStr = CommonUtil.gson.toJson(nmm);

		try {
			String response = CommonUtil.getResources(upploadNewsMateriaUrl1, jsonStr);
			mediaModel = CommonUtil.gson.fromJson(response, MediaModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mediaModel;
	}

	// 上传图文素材中的图片获取url
	public static MediaModel uploadPicFromNewsMaterial(String picFromNewsUrl) {
		MediaModel mediaModel = null;
		String uploadPicFromNewsMaterialUrl1 = uploadPicFromNewsMaterialUrl;
		uploadPicFromNewsMaterialUrl1 = uploadPicFromNewsMaterialUrl1.replace("TYPE", "POST");
		uploadPicFromNewsMaterialUrl1 = uploadPicFromNewsMaterialUrl1.replace("ACCESS_TOKEN",
				AccessTokenUtil.access_token);

		try {
			String response = CommonUtil.getMediaResource(uploadPicFromNewsMaterialUrl1, picFromNewsUrl);
			mediaModel = CommonUtil.gson.fromJson(response, MediaModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mediaModel;
	}
	
	//删除永久素材
	public static ErrorModel delMaterial(MediaModel mediaModel){
		ErrorModel errorModel = null;
		
		String delMaterialUrl1 = delMaterialUrl;
		delMaterialUrl1 = delMaterialUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);
		
		String jsonStr = CommonUtil.gson.toJson(mediaModel);
		
		try {
			String response = CommonUtil.getResources(delMaterialUrl1, jsonStr);
			errorModel = CommonUtil.gson.fromJson(response, ErrorModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return errorModel;
	}
	
	//修改永久图文素材
	public static ErrorModel updateNewsMaterial(NewsMaterialModel nmm){
		ErrorModel errorModel = null;
		
		String updateNewsMaterialUrl1 = updateNewsMaterialUrl;
		updateNewsMaterialUrl1 = updateNewsMaterialUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);

		String jsonStr = CommonUtil.gson.toJson(nmm);

		try {
			String response = CommonUtil.getResources(updateNewsMaterialUrl1, jsonStr);
			errorModel = CommonUtil.gson.fromJson(response, MediaModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return errorModel;
	}

}
