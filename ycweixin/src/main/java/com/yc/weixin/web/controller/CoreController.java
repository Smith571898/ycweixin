package com.yc.weixin.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yc.weixin.bean.CoreMessage;
import com.yc.weixin.bean.WeChatUser;
import com.yc.weixin.biz.CoreBiz;
import com.yc.weixin.biz.UserBiz;
import com.yc.weixin.model.UserModel;
import com.yc.weixin.utils.AccessTokenUtil;
import com.yc.weixin.utils.FileLoadUtil;
import com.yc.weixin.utils.SignUtil;
import com.yc.weixin.utils.UserInfoUtil;

@Controller
public class CoreController {

	private UserInfoUtil  userInfoUtil =new UserInfoUtil();
	private FileLoadUtil  fileLoadUtil=new FileLoadUtil();
	
	@Resource(name="userBizImpl")
	private  UserBiz userBiz;
	@Resource(name="coreBizImpl")
	private CoreBiz cb;
	
	
	
	//get请求，验证请求来源是否为微信服务器
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
	public void file(HttpServletRequest request){
		try {
			String access_token=AccessTokenUtil.access_token;
			List<UserModel> list=userInfoUtil.getAllUserInfo(access_token);
			List<WeChatUser> weChatUserList=new ArrayList<WeChatUser>();
			for(UserModel um:list){
				WeChatUser wu=new WeChatUser();
				wu.setOpenid(um.getOpenid());
				wu.setNickname(um.getNickname());
				String aaa=um.getHeadimgurl();
				System.out.println(aaa);
				String headImgUrl=fileLoadUtil.fileupload(request, um.getHeadimgurl());
				wu.setHeadimgurl(headImgUrl);
				wu.setAddress(um.getCountry()+um.getProvince()+um.getCity());
				if(um.getSex()==1){
					wu.setSex("男");
				}else if(um.getSex()==2){
					wu.setSex("女");
				}else{
					wu.setSex("未知");
				}
				if(um.getSubscribe()==1){
					wu.setSubscribe("已关注");
				}else{
					wu.setSubscribe("未关注");
				}
				wu.setSubscribe_time(um.getSubscribe_time());
				
				weChatUserList.add(wu);
			}
			userBiz.AddUserInfo(weChatUserList);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	/*	FileLoadUtil flu = new FileLoadUtil();
		try {
			List<String> userImg = UserInfoUtil.getUserHeadImgUrl();
			for(String url:userImg){
				String filename=	flu.fileupload(req, url);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
