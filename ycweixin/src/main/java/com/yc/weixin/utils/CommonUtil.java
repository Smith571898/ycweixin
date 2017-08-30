package com.yc.weixin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.yc.weixin.model.ArticleMaterialModel;
import com.yc.weixin.model.MediaModel;

import java.io.OutputStream;

/**
 * 一个公共的工具类
 * 
 * @author yhy
 *
 */
public class CommonUtil {

	public static Gson gson = new Gson();

	// 相对路径与绝对路径
	public static Map<String, String> picWithAbsolute = new HashMap<String, String>();

	public static List<ArticleMaterialModel> news = new ArrayList<ArticleMaterialModel>();

	// 通过url与微信端建立链接,得到微信端响应的信息
	public static String getResources(String uri, String jsonStr) throws IOException {
		URL url = new URL(uri);
		URLConnection connUrl = url.openConnection();
		connUrl.setDoOutput(true);

		if (jsonStr != null) {
			OutputStream out = connUrl.getOutputStream();
			out.write(jsonStr.getBytes("utf-8"));
			out.flush();
			out.close();
		}

		InputStreamReader is = new InputStreamReader(connUrl.getInputStream(), "utf-8");

		char[] buf = new char[10 * 1024];
		int length = 0;
		String response = "";
		while ((length = is.read(buf, 0, buf.length)) != -1) {
			response = new String(buf, 0, length);
		}
		is.close();

		System.out.println(response);

		return response;
	}

	// 上传媒体文件
	public static String getMediaResource(String uploadMediaUrl, String mediaFileUrl) throws IOException {
		String boundary = "-----------------" + UUID.randomUUID().toString();
		URL uploadUrl = new URL(uploadMediaUrl);
		HttpURLConnection uploadConnUrl = (HttpURLConnection) uploadUrl.openConnection();
		uploadConnUrl.setDoOutput(true);
		uploadConnUrl.setDoInput(true);
		uploadConnUrl.setRequestMethod("POST");
		uploadConnUrl.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
		OutputStream out = uploadConnUrl.getOutputStream();

		File file = new File(mediaFileUrl);
		FileInputStream fis = new FileInputStream(file);
		int index = file.getAbsolutePath().indexOf(".");
		String fileExt = file.getAbsolutePath().substring(index);

		out.write(("--" + boundary + "\r\n").getBytes());
		out.write((String.format("Content-Disposition:form-data;name=\"media\";filelength=\"" + file.length()
				+ "\";filename=\"file1%s\"\r\n", fileExt)).getBytes());
		out.write((String.format("Content-Type: %s\r\n\r\n", FileSuffixUtil.getContentType(fileExt))).getBytes());

		byte[] buf = new byte[10 * 1024 * 10];
		int length = 0;
		while ((length = fis.read(buf)) != -1) {
			out.write(buf, 0, length);
		}

		out.write(("\r\n--" + boundary + "--\r\n").getBytes());

		out.close();
		fis.close();

		InputStream is = uploadConnUrl.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);

		StringBuffer sb = new StringBuffer();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}

		br.close();
		isr.close();
		is.close();
		is = null;
		uploadConnUrl.disconnect();

		return sb.toString();
	}
	
}
