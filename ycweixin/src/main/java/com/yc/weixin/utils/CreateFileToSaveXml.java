package com.yc.weixin.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

public class CreateFileToSaveXml {
	
	//从微信号传过来的数据存到xml中
	public static void saveToXml(HttpServletRequest req) throws IOException{
		File root = new File(req.getSession().getServletContext().getRealPath("/"));
		File rootfile = root.getParentFile();
		String filepath = rootfile + "/logs/";
		File filedir = new File(filepath);
		if(!filedir.exists()){
			filedir.mkdirs();
		}
		File file = new File(filedir+"/xml.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		InputStream in = req.getInputStream();
		FileOutputStream fos = new FileOutputStream(file,true);
		byte[] buf = new byte[10*1024];
		int length = -1;
		length = in.read(buf);
		fos.write(buf, 0, length);
		fos.write(new String("\r\n").getBytes());
		fos.close();
		in.close();
	}
}
