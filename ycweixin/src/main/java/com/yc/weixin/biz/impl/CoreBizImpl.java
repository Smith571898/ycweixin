package com.yc.weixin.biz.impl;

import java.io.File;
import java.io.IOException;
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
import com.yc.weixin.utils.MessageUtil;
import com.yc.weixin.utils.UserInfoUtil;

@Service
public class CoreBizImpl implements CoreBiz {
	private Map<String, String> reqMap = new HashMap<String, String>();
	private String msgType = "";
	private String respXml = "";
	
	@Resource(name="chatBizImpl")
	private ChatBiz cb;
	
	@Resource(name="messageBizImpl")
	private MessageBiz mb;

	@Resource(name="userBizImpl")
	private UserBiz ub;
	
	@Override
	public String processXml(HttpServletRequest req) {
		try {
			reqMap = MessageUtil.parseXml(req);
			msgType = reqMap.get("MsgType");
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

	// 关注事件
	//TODO:从数据库查询数据
	private void eventWhenSubscribe() {
		String toUserName = reqMap.get("ToUserName");
		String fromUserName = reqMap.get("FromUserName");
		
		saveUser(fromUserName);
		
		List<FollowPushMessage> list = mb.findFollowPushMessage(null);
		List<Article> articles = new ArrayList<Article>();

		for(FollowPushMessage fpm:list){
			Article article = new Article();
			article.setDescription(fpm.getFcontent());
			article.setPicUrl("http://119.23.39.190/pic/6.jpg");
			article.setTitle(fpm.getFtitle());
			article.setUrl("http://www.hyycinfo.com/");
			
			articles.add(article);
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

	}

	//TODO:保存用户到数据库
	private void saveUser(String openid){
		try {
			UserModel um = UserInfoUtil.getUserInfo(AccessTokenUtil.access_token, openid);
			WeChatUser wcu = new WeChatUser();
			wcu.setOpenid(um.getOpenid());
			ub.AddUser(wcu);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
