package com.yc.weixin.biz.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.yc.weixin.biz.CoreBiz;
import com.yc.weixin.resp.message.TextMessage;
import com.yc.weixin.service.ChatService;
import com.yc.weixin.utils.MessageUtil;

@Service
public class CoreBizImpl implements CoreBiz {

	public String processXml(HttpServletRequest req) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "发送任意文本，我们开始聊天吧！";
		try {
		    // 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(req);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息创建时间
			String createTime = requestMap.get("CreateTime");
			
			if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)){
				String content = requestMap.get("Content");				
				respContent = ChatService.chat(fromUserName, createTime, content);
				System.out.println(respContent);
			}
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(respContent);
			
			
			
			// 将文本消息对象转换成xml
			respXml = MessageUtil.messageToXml(textMessage);
			
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		return respXml;
	}

}
