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

public class MediaUtil {
	
	//新增临时素材
	private static String uploadTempMediaUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	//新增永久素材
	private static String uploadMaterialUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
	
	public static MediaModel uploadTempMedia(String type,String mediaFileUrl) {
		MediaModel mediaModel = null;
		uploadTempMediaUrl = uploadTempMediaUrl.replace("TYPE",type);
		uploadTempMediaUrl = uploadTempMediaUrl.replace("ACCESS_TOKEN","l7gZG4j1ZdItEf9mbpjQGoRnvAtZ1PZ2Xc8OJMd5Qsn-8644O5pmFnX7q5UwhD2dRf-rahiGcY9LCCE1ANQlSGT-wSvDDQdPS-eo5157d6Tl90TY13A8abcVCrgj7wOVQMUcAFACYG");
		
		try {
			getMediaModel(uploadTempMediaUrl,mediaFileUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mediaModel;
	}
	
	public static MediaModel uploadMateria(String type,String mediaFileUrl){
		MediaModel mediaModel = null;
		uploadMaterialUrl = uploadMaterialUrl.replace("TYPE",type);
		uploadMaterialUrl = uploadMaterialUrl.replace("ACCESS_TOKEN","l7gZG4j1ZdItEf9mbpjQGoRnvAtZ1PZ2Xc8OJMd5Qsn-8644O5pmFnX7q5UwhD2dRf-rahiGcY9LCCE1ANQlSGT-wSvDDQdPS-eo5157d6Tl90TY13A8abcVCrgj7wOVQMUcAFACYG");
		
		try {
			getMediaModel(uploadMaterialUrl,mediaFileUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mediaModel;
	}
	
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
		out.write((String.format("Content-Disposition:form-data;name=\"media\";filename=\"file1%s\"\r\n", fileExt)).getBytes());
		out.write((String.format("Content-Type: %s\r\n\r\n", FileSuffixUtil.getContentType(fileExt))).getBytes());
		
		byte[] buf = new byte[10*1024];
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