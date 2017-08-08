package com.yc.weixin.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yc.weixin.bean.CoreMessage;
import com.yc.weixin.biz.CoreBiz;
import com.yc.weixin.biz.impl.CoreBizImpl;
import com.yc.weixin.model.AcessTokenModel;
import com.yc.weixin.utils.AccessTokenUtil;
import com.yc.weixin.utils.FileLoadUtil;
import com.yc.weixin.utils.SignUtil;

@Controller
public class CoreController {
	
	@Resource(name="coreBizImpl")
	private CoreBiz cb;
	
	@RequestMapping(path = "hello", method=RequestMethod.GET)
	public void helloGet(CoreMessage core, HttpServletResponse resp)throws NoSuchAlgorithmException, IOException{
		PrintWriter out = resp.getWriter();
		if(SignUtil.checkSignature(core.getSignature(), core.getTimestamp(), core.getNonce())){
			out.print(core.getEchostr());
		}
		
		out.flush();
		out.close();
		out = null;
	}
	
	@RequestMapping(path = "hello", method=RequestMethod.POST)
	public void helloPost(CoreMessage core, HttpServletResponse resp, HttpServletRequest req)throws NoSuchAlgorithmException, IOException, DocumentException{
		PrintWriter out = resp.getWriter();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if(SignUtil.checkSignature(core.getSignature(), core.getTimestamp(), core.getNonce())){
//			CreateFileToSaveXml.saveToXml(req);
			String respXml = cb.processXml(req);
			out.print(respXml);
		}
		
		out.flush();
		out.close();
		out = null;
	}
	
	@RequestMapping(path = "file.action", method = RequestMethod.GET)
	public void file(HttpServletRequest req){
		FileLoadUtil flu = new FileLoadUtil();
		try {
			List<String> userImg = AccessTokenUtil.getUserHeadImgUrl();
			flu.fileupload(req, userImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
