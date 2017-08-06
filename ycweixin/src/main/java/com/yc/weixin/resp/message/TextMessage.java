package com.yc.weixin.resp.message;

import java.io.Serializable;

public class TextMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -375491447959241906L;
	//回复的消息内容
	private String Content;
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		this.Content = content;
	}
	@Override
	public String toString() {
		return super.toString()+"TextMessage [content=" + Content + "]";
	}
}
