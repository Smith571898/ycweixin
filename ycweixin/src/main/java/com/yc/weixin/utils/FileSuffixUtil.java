package com.yc.weixin.utils;

public class FileSuffixUtil {

	//将传过来的contentType转换成文件后缀名
	public static String getFileExt(String contentType){
		String fileExt = "";
		if("image/jpeg".equals(contentType)){
			fileExt = ".jpg";
		} else if("audio/mpeg".equals(contentType)){
			fileExt = ".mp3";
		} else if("audio/amr".equals(contentType)){
			fileExt = ".amr";
		} else if("video/mp4".equals(contentType)){
			fileExt = ".mp4";
		} else if("video/mpeg4".equals(contentType)){
			fileExt = ".mp4";
		}
		return fileExt;
	}
	
	//将传过来的文件后缀名转换成contentType
	public static String getContentType(String fileExt){
		String contentType = "";
		if(".jpg".equals(fileExt)){
			contentType = "image/jpeg";
		} else if(".mp3".equals(fileExt)){
			contentType = "audio/mpeg";
		} else if(".amr".equals(fileExt)){
			contentType = "audio/amr";
		} else if(".mp4".equals(fileExt)){
			contentType = "video/mp4";
		}
		return contentType;
	}
}
