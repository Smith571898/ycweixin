package com.yc.weixin.req.message;

import java.io.Serializable;

public class TextMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1325544949901526665L;
	//消息内容
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
