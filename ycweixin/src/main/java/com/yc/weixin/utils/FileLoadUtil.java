package com.yc.weixin.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yc.weixin.biz.UserBiz;
import com.yc.weixin.biz.impl.UserBizImpl;

public class FileLoadUtil {

	private String weburl;

	//通过时间生成文件名
	public String genNewFilePrefixName() {
		// 生成新的文件名
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("SSyyyymmddHHmmss");
		String filePrefixName = sdf.format(d); // 文件的前缀名
		return filePrefixName;
	}
	
	//生成图片所在的文件夹
	private String genNewFileDir(HttpServletRequest req){
		Calendar c = Calendar.getInstance();
		String tomcatdir = req.getRealPath("/"); // xxx/xxx/webapps/ycweixin
		File tomcatFile = new File(tomcatdir);
		File webapppath = tomcatFile.getParentFile(); // xxx/xxx/webapps/head/2017/08/
		String rootpath = webapppath + File.separator + "head" + File.separator ;
		weburl = "../head/" + c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/";
		File picpath = new File(rootpath);
		if(!picpath.exists()){
			picpath.mkdirs();
		}
		return picpath.getAbsolutePath();
	}
	
	//生成图片路径
	private String getFilepath(HttpServletRequest req){
		String dir = genNewFileDir(req);
		String name = genNewFilePrefixName();
		
		String fileExt = ".jpg";
		weburl += name+ fileExt;
		return dir+File.separator+name+fileExt;
	}
	
	//图片下载
	public String fileupload(HttpServletRequest req , String uri) throws Exception{
		
			URL url = new URL(uri);
			URLConnection urlconn = url.openConnection();

			InputStream is = urlconn.getInputStream();

			byte[] buf = new byte[10*1024];
			int length = 0;
			//生成图片路径
			String filepath = getFilepath(req);
			
			File file=new File(filepath);
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream out=new FileOutputStream(file);
			
			while ((length = is.read(buf, 0, buf.length)) != -1) {
				out.write(buf, 0, length);
			}
			
			out.flush();
			out.close();
			return filepath.substring(filepath.lastIndexOf("\\")+1);
		}
	}

