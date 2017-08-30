package com.yc.weixin.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yc.weixin.model.MediaModel;
import com.yc.weixin.utils.FileLoadUtil;
import com.yc.weixin.utils.MediaUtil;

@RestController
public class TempSourceController {
	private  FileLoadUtil fileLoadUtil =new FileLoadUtil();
	private MediaUtil mediaUtil =new MediaUtil();

	@RequestMapping(value="douploadTemppicbat.action",method=RequestMethod.POST)
	public  String  Sourceupload(@RequestParam(value="fpics",required=false) MultipartFile[]  file,HttpServletRequest request){

		for (MultipartFile mf : file) {
			try {
			String fileurl=	fileLoadUtil.upload(mf, request, "source");//E:\apache-tomcat-8.0.44\webapps\source\b8dbf687fcb4435cafd63e7d2145d358.png
			MediaModel mediaModel=new MediaModel();
			mediaModel=mediaUtil.uploadTempMedia("image", fileurl);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	System.out.println(1);
	return null;
		
	}
	
	@RequestMapping(value="douploadTempVideobat.action",method=RequestMethod.POST)
	public  String  Sourceupload1(@RequestParam(value="fvideos",required=false) MultipartFile[]  file,HttpServletRequest request){

		for (MultipartFile mf : file) {
			try {
			String fileurl=	fileLoadUtil.upload(mf, request, "video");//E:\apache-tomcat-8.0.44\webapps\source\b8dbf687fcb4435cafd63e7d2145d358.png
			MediaModel mediaModel=new MediaModel();
			mediaModel=mediaUtil.uploadTempMedia("video", fileurl);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	System.out.println(1);
	return null;
		
	}
	
	
	@RequestMapping(value="douploadTempAudiobat.action",method=RequestMethod.POST)
	public  String  Sourceupload2(@RequestParam(value="faudios",required=false) MultipartFile[]  file,HttpServletRequest request){

		for (MultipartFile mf : file) {
			try {
			String fileurl=	fileLoadUtil.upload(mf, request, "voice");//E:\apache-tomcat-8.0.44\webapps\source\b8dbf687fcb4435cafd63e7d2145d358.png
			MediaModel mediaModel=new MediaModel();
			mediaModel=mediaUtil.uploadTempMedia("voice", fileurl);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	System.out.println(1);
	return null;
		
	}
}
