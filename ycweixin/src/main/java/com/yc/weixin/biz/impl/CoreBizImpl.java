package com.yc.weixin.biz.impl;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.yc.weixin.bean.FollowPushMessage;
import com.yc.weixin.bean.WeChatUser;
import com.yc.weixin.biz.ChatBiz;
import com.yc.weixin.biz.CoreBiz;
import com.yc.weixin.biz.MessageBiz;
import com.yc.weixin.biz.UserBiz;
import com.yc.weixin.model.UserModel;
import com.yc.weixin.resp.message.Article;
import com.yc.weixin.resp.message.NewsMessage;
import com.yc.weixin.resp.message.TextMessage;
import com.yc.weixin.utils.AccessTokenUtil;
import com.yc.weixin.utils.FileLoadUtil;
import com.yc.weixin.utils.MessageUtil;
import com.yc.weixin.utils.UserInfoUtil;

@Service
public class CoreBizImpl implements CoreBiz {
	private Map<String, String> reqMap = new HashMap<String, String>();
	private String msgType = "";
	private String respXml = "";
	private FileLoadUtil  fileLoadUtil=new FileLoadUtil();
	private UserInfoUtil  userInfoUtil =new UserInfoUtil();

	@Resource(name="chatBizImpl")
	private ChatBiz cb;
	
	@Resource(name="messageBizImpl")
	private MessageBiz mb;

	@Resource(name="userBizImpl")
	private UserBiz ub;
	private HttpServletRequest req;
	@Override
	public String processXml(HttpServletRequest req) {
		try {
			reqMap = MessageUtil.parseXml(req);
			msgType = reqMap.get("MsgType");
			respXml = "";
			this.req = req;
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

		getRespContent();
		
		return respXml;
	}

	// 获取回复信息
	private void getRespContent() {
		if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
			getRespContentWhenEvent();
		} else {
			getRespContentWhenMessage();
		}
	}

	// 接收信息为消息时的回复
	private void getRespContentWhenMessage() {
		if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
			messageWhenText();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_IMAGE.equals(msgType)) {
			messageWhenImage();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_LINK.equals(msgType)) {
			messageWhenLink();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_LOCATION.equals(msgType)) {
			messageWhenLocation();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_VIDEO.equals(msgType)) {
			messageWhenVideo();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_VOICE.equals(msgType)) {
			messageWhenVoice();
		}
	}

	// 接收信息为事件时的回复
	private void getRespContentWhenEvent() {
		String event = reqMap.get("Event");
		if (MessageUtil.REQ_EVENT_TYPE_CLICK.equals(event)) {
			eventWhenClick();
		} else if (MessageUtil.REQ_EVENT_TYPE_LOCATION.equals(event)) {
			eventWhenLocation();
		} else if (MessageUtil.REQ_EVENT_TYPE_SCAN.equals(event)) {
			eventWhenScan();
		} else if (MessageUtil.REQ_EVENT_TYPE_SUBSCRIBE.equals(event)) {
			eventWhenSubscribe();
		} else if (MessageUtil.REQ_EVENT_TYPE_UNSUBSCRIBE.equals(event)) {
			eventWhenUnsubscribe();
		} else if (MessageUtil.REQ_EVENT_TYPE_RESP.equals(event)) {
			eventWhenResp();
		}
	}

	// 文本消息
	private void messageWhenText() {
		String toUserName = reqMap.get("ToUserName");
		String fromUserName = reqMap.get("FromUserName");
		String response = "格格爱吃周黑鸭!!!";
		// 消息创建时间
		String createTime = reqMap.get("CreateTime");

		String content = reqMap.get("Content");
		response = cb.chat(fromUserName, createTime, content);
		
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setToUserName(fromUserName);
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(response);
		
		respXml = MessageUtil.messageToXml(textMessage);
	}

	// 图片消息
	private void messageWhenImage() {

	}

	// 链接消息
	private void messageWhenLink() {

	}

	// 定位消息
	private void messageWhenLocation() {

	}

	// 视频消息
	private void messageWhenVideo() {

	}

	// 语音消息
	private void messageWhenVoice() {

	}

	// 点击事件
	private void eventWhenClick() {

	}

	// 定位事件
	private void eventWhenLocation() {

	}

	// 扫描带参二维码事件
	private void eventWhenScan() {

	}

	//图文发送响应
	private void eventWhenResp() {
		String status = reqMap.get("Status");
		if(status.equals("send success")){
			//发送成功
			
		} else if(status.equals("send fail")){
			//发送失败
			
		} else if(status.equals("err(10001)")){
			//审核失败  涉嫌广告
			
		} else if(status.equals("err(20001)")){
			//审核失败  涉嫌政治
			
		} else if(status.equals("err(20004)")){
			//审核失败  涉嫌社会
			
		} else if(status.equals("err(20002)")){
			//审核失败  涉嫌色情 
			
		} else if(status.equals("err(20006)")){
			//审核失败  涉嫌违法犯罪
			
		} else if(status.equals("err(20008)")){
			//审核失败  涉嫌欺诈 
			
		} else if(status.equals("err(20013)")){
			//审核失败  涉嫌版权 
			
		} else if(status.equals("err(22000)")){
			//审核失败  涉嫌互推(互相宣传) 
			
		} else if(status.equals("err(21000)")){
			//审核失败  涉嫌其他
			
		}
	}

	// 关注事件

	private void eventWhenSubscribe() {
		String toUserName = reqMap.get("ToUserName");
		String fromUserName = reqMap.get("FromUserName");
		
		try {
			saveUser(fromUserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<FollowPushMessage> list = mb.findFollowPushMessage(null);
		List<Article> articles = new ArrayList<Article>();

		for(FollowPushMessage fpm:list){
			if(fpm.getIsfollowpush().equals("是")){
			Article article = new Article();
			article.setDescription(fpm.getFcontent());
			article.setPicUrl("http://dnfyia.natappfree.cc/pic/6.jpg");
			article.setTitle(fpm.getFtitle());
			article.setUrl("http://www.hyycinfo.com/");
			
			articles.add(article);
			}
		}
		
		NewsMessage nm = new NewsMessage();
		nm.setToUserName(fromUserName);
		nm.setFromUserName(toUserName);
		nm.setCreateTime(new Date().getTime());
		nm.setArticleCount(articles.size());
		nm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		nm.setArticles(articles);
		
		respXml = MessageUtil.messageToXml(nm);
	}

	// 取消关注事件
	private void eventWhenUnsubscribe() {
		String openid = reqMap.get("FromUserName");
		WeChatUser wcu = new WeChatUser();
		wcu.setOpenid(openid);
		wcu.setSubscribe("未关注");
		ub.updateUserisFollow(wcu);

	}

	//TODO:保存用户到数据库
	/**
	 * 
	 * 新用户关注 存数据到数据库  回归用户重新关注   更新最新的微信信息
	 * @param openid
	 * @throws Exception
	 */
	private void saveUser(String openid) throws Exception{
		try {
			UserModel um = UserInfoUtil.getUserInfo(AccessTokenUtil.access_token, openid);
			System.out.println(AccessTokenUtil.access_token);
			WeChatUser wcu = new WeChatUser();
			wcu.setOpenid(um.getOpenid());
			System.out.println(openid);
			wcu.setNickname(um.getNickname());
			System.out.println(wcu);
			String headImgUrl=fileLoadUtil.fileupload(req, um.getHeadimgurl());
			wcu.setHeadimgurl(headImgUrl.substring(headImgUrl.lastIndexOf("\\")+1));
			
			wcu.setAddress(um.getCountry()+um.getProvince()+um.getCity());
			if(um.getSex()==1){
				wcu.setSex("男");
			}else if(um.getSex()==2){
				wcu.setSex("女");
			}
			wcu.setSubscribe("已关注");
			wcu.setSubscribe_time(um.getSubscribe_time());
			WeChatUser weChatUser =ub.CheckUserisExist(wcu);
			
			if(weChatUser==null){			
				ub.AddUser(wcu);		
			}else if(weChatUser!=null){			
					ub.updateUserSubscribe(wcu);//如果该用户以前关注过，重新关注 就更新最新的信息					
		
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将5
	 * 1
	 * 
	 */
	/***
	 * 一键获取所有的用户名  并存入数据库
	 */
	private void weChatHeaddownload(){

		try {
			String access_token=AccessTokenUtil.access_token;
			List<UserModel> list=userInfoUtil.getAllUserInfo(access_token);
			List<WeChatUser> weChatUserList=new ArrayList<WeChatUser>();
			for(UserModel um:list){
				WeChatUser wu=new WeChatUser();
				wu.setOpenid(um.getOpenid());
				wu.setNickname(um.getNickname());

				String headImgUrl=fileLoadUtil.fileupload(req, um.getHeadimgurl());
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
		ub.AddUserInfo(weChatUserList);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	}
}
