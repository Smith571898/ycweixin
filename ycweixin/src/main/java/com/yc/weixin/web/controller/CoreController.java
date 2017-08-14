package com.yc.weixin.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yc.weixin.bean.CoreMessage;
import com.yc.weixin.biz.ChatBiz;
import com.yc.weixin.biz.CoreBiz;
import com.yc.weixin.utils.FileLoadUtil;
import com.yc.weixin.utils.SignUtil;
import com.yc.weixin.utils.UserInfoUtil;

@Controller
public class CoreController {
	
	@Resource(name="coreBizImpl")
	private CoreBiz cb;
	
	@Resource(name="chatBizImpl")
	private ChatBiz chatBiz;
	
	//get请求，验证请求来源是否为微信服务器
	@RequestMapping(path = "hello", method=RequestMethod.GET)
	public void helloGet(CoreMessage core, HttpServletResponse resp)throws NoSuchAlgorithmException, IOException{
		PrintWriter out = resp.getWriter();
		if(SignUtil.checkSignature(core.getSignature(), core.getTimestamp(), core.getNonce())){
			out.print(core.getEchostr());
		}

		File indexDir = new File(chatBiz.getIndexDir());
		// 如果索引目录不存在则创建索引
		if (!indexDir.exists()){
			chatBiz.createIndex();
		}
		
		out.flush();
		out.close();
		out = null;
	}
	
	//POST请求，接受从微信端发送过来的信息，一般是关注者向公众号发送的信息
	@RequestMapping(path = "hello", method=RequestMethod.POST)
	public void helloPost(CoreMessage core, HttpServletResponse resp, HttpServletRequest req)throws NoSuchAlgorithmException, IOException, DocumentException{
		PrintWriter out = resp.getWriter();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if(SignUtil.checkSignature(core.getSignature(), core.getTimestamp(), core.getNonce())){
			String respXml = cb.processXml(req);
			out.print(respXml);
		}
		
		out.flush();
		out.close();
		out = null;
	}
	
	//下载关注者头像到服务器
	@RequestMapping(path = "file.action", method = RequestMethod.GET)
	public void file(HttpServletRequest req){
		FileLoadUtil flu = new FileLoadUtil();
		try {
			List<String> userImg = UserInfoUtil.getUserHeadImgUrl();
			flu.fileupload(req, userImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
