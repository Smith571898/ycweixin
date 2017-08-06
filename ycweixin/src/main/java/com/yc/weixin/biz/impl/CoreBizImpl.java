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
import com.yc.weixin.utils.MessageUtil;

@Service
public class CoreBizImpl implements CoreBiz {

	public String processXml(HttpServletRequest req) {
		String respXml = null;
		String respContent = "未知的消息类型!";
		try {
			Map<String, String> reqMap = MessageUtil.parseXml(req);
			String fromUserName = reqMap.get("FromUserName");
			String toUserName = reqMap.get("ToUserName");
			String msgType = reqMap.get("MsgType");
			
			TextMessage textMessage = new TextMessage();
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setToUserName(fromUserName);
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)){
				respContent = "this is a bad tangge!!!";
			}
			textMessage.setContent(respContent);
			respXml = MessageUtil.messageToXml(textMessage);
			
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		return respXml;
	}

}
