package com.yc.weixin.biz.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.yc.weixin.biz.CoreBiz;
import com.yc.weixin.resp.message.TextMessage;
import com.yc.weixin.utils.MessageUtil;

@Service
public class CoreBizImpl implements CoreBiz {
	private Map<String, String> reqMap = new HashMap<String, String>();
	private String msgType = "";
	private String respXml = "";

	@Override
	public String processXml(HttpServletRequest req) {
		try {
			reqMap = MessageUtil.parseXml(req);
			msgType = reqMap.get("MsgType");
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		String respContent = "未知的消息类型";
		String respXml = null;
		String fromUserName = reqMap.get("FromUserName");
		String toUserName = reqMap.get("ToUserName");

		//TODO:封装要回送的消息,不只是文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setToUserName(fromUserName);
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

		respContent = getRespContent();

		textMessage.setContent(respContent);
		respXml = MessageUtil.messageToXml(textMessage);

		return respXml;
	}

	//获取回复信息
	private String getRespContent() {
		String respContent = "";
		if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
			respContent = getRespContentWhenEvent();
		} else {
			respContent = getRespContentWhenMessage();
		}
		return respContent;
	}

	//接收信息为消息时的回复
	private String getRespContentWhenMessage() {
		String respContent = "";
		if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
			respContent = messageWhenText();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_IMAGE.equals(msgType)) {
			respContent = messageWhenImage();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_LINK.equals(msgType)) {
			respContent = messageWhenLink();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_LOCATION.equals(msgType)) {
			respContent = messageWhenLocation();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_VIDEO.equals(msgType)) {
			respContent = messageWhenVideo();
		} else if (MessageUtil.REQ_MESSAGE_TYPE_VOICE.equals(msgType)) {
			respContent = messageWhenVoice();
		}
		return respContent;
	}

	//接收信息为事件时的回复
	private String getRespContentWhenEvent() {
		String respContent = "";
		if (MessageUtil.REQ_EVENT_TYPE_CLICK.equals(msgType)) {
			respContent = eventWhenClick();
		} else if (MessageUtil.REQ_EVENT_TYPE_LOCATION.equals(msgType)) {
			respContent = eventWhenLocation();
		} else if (MessageUtil.REQ_EVENT_TYPE_SCAN.equals(msgType)) {
			respContent = eventWhenScan();
		} else if (MessageUtil.REQ_EVENT_TYPE_SUBSCRIBE.equals(msgType)) {
			respContent = eventWhenSubscribe();
		} else if (MessageUtil.REQ_EVENT_TYPE_UNSUBSCRIBE.equals(msgType)) {
			respContent = eventWhenUnsubscribe();
		}
		return respContent;
	}
	
	//文本消息
	private String messageWhenText(){
		String response = "格格爱吃周黑鸭!!!";
		
		return response;
	}
	
	//图片消息
	private String messageWhenImage(){
		String response = "";
		
		return response;
	}
	
	//链接消息
	private String messageWhenLink(){
		String response = "";
		
		return response;
	}
	
	//定位消息
	private String messageWhenLocation(){
		String response = "";
		
		return response;
	}
	
	//视频消息
	private String messageWhenVideo(){
		String response = "";
		
		return response;
	}
	
	//语音消息
	private String messageWhenVoice(){
		String response = "";
		
		return response;
	}
	
	//点击事件
	private String eventWhenClick(){
		String response = "";
		
		return response;
	}
	
	//定位事件
	private String eventWhenLocation(){
		String response = "";
		
		return response;
	}
	
	//扫描带参二维码事件
	private String eventWhenScan(){
		String response = "";
		
		return response;
	}
	
	//关注事件
	private String eventWhenSubscribe(){
		String response = "";
		
		return response;
	}
	
	//取消关注事件
	private String eventWhenUnsubscribe(){
		String response = "";
		
		return response;
	}
	
}
