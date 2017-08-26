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

import com.google.gson.Gson;
import com.yc.weixin.model.MediaModel;

import com.yc.weixin.model.NewsMaterialModel;

public class MediaUtil {
	//新增临时素材
	private static final String uploadTempMediaUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	//新增永久素材
	private static final String uploadMaterialUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
	
	//新增永久图文素材
	private static final String uploadNewsMateriaUrl = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	
	//上传临时素材
	public static MediaModel uploadTempMedia(String type,String mediaFileUrl) {
		MediaModel mediaModel = null;
		System.out.println(type);
		String uploadTempMediaUrl1=uploadMaterialUrl;
		 uploadTempMediaUrl1 = uploadTempMediaUrl1.replace("TYPE",type);
		 uploadTempMediaUrl1 = uploadTempMediaUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);
		
		try {
			getMediaModel(uploadTempMediaUrl1,mediaFileUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(mediaModel+"temp");
		return mediaModel;
	}
	
	//上传永久素材
	public static MediaModel uploadMateria(String type,String mediaFileUrl){
		MediaModel mediaModel = null;
		String uploadTempMediaUrl1=uploadMaterialUrl;
		 uploadTempMediaUrl1 = uploadTempMediaUrl1.replace("TYPE",type);
		 uploadTempMediaUrl1 = uploadTempMediaUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);
		try {
			getMediaModel(uploadTempMediaUrl1,mediaFileUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(mediaModel);
		return mediaModel;
	}
	
	//上传永久图文素材
	public static MediaModel uploadNewsMateria(NewsMaterialModel nmm){
		MediaModel mediaModel = null;
		String uploadTempMediaUrl1=uploadNewsMateriaUrl;
		uploadTempMediaUrl1 = uploadTempMediaUrl1.replace("ACCESS_TOKEN", AccessTokenUtil.access_token);
		
		String jsonStr = CommonUtil.gson.toJson(nmm);
		
		try {
			String response = CommonUtil.getResources(uploadTempMediaUrl1, jsonStr);
			mediaModel = CommonUtil.gson.fromJson(response, MediaModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mediaModel;
	}
	
	//上传

	private static MediaModel getMediaModel(String uploadMediaUrl,String mediaFileUrl) throws IOException{
		MediaModel mediaModel = null;
		String boundary = "-----------------"+UUID.randomUUID().toString();
		URL uploadUrl = new URL(uploadMediaUrl);
		HttpURLConnection uploadConnUrl = (HttpURLConnection) uploadUrl.openConnection();
		uploadConnUrl.setDoOutput(true);
		uploadConnUrl.setDoInput(true);
		uploadConnUrl.setRequestMethod("POST");
		uploadConnUrl.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
		OutputStream out = uploadConnUrl.getOutputStream();
		
		File file = new File(mediaFileUrl);
		FileInputStream fis = new FileInputStream(file);
		int index = file.getAbsolutePath().indexOf(".");
		String fileExt = file.getAbsolutePath().substring(index);

		out.write(("--"+boundary+"\r\n").getBytes());
		out.write((String.format("Content-Disposition:form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\"file1%s\"\r\n", fileExt)).getBytes());
		out.write((String.format("Content-Type: %s\r\n\r\n", FileSuffixUtil.getContentType(fileExt))).getBytes());
		
		byte[] buf = new byte[10*1024*10];
		int length = 0;
		while((length=fis.read(buf)) != -1){
			out.write(buf, 0, length);
		}
		
		out.write(("\r\n--"+boundary+"--\r\n").getBytes());
		
		out.close();
		fis.close();
		
		InputStream is = uploadConnUrl.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"utf-8");
		BufferedReader br = new BufferedReader(isr);

		StringBuffer sb = new StringBuffer();
		String str = null;
		while((str = br.readLine())!=null){
			sb.append(str);
		}
		
		br.close();
		isr.close();
		is.close();
		is = null;
		uploadConnUrl.disconnect();
		
		System.out.println(sb.toString());
		
		Gson gson = new Gson();
		mediaModel = gson.fromJson(sb.toString(), MediaModel.class);
		
		return mediaModel;

	} 
	
}
