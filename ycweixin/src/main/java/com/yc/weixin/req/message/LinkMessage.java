package com.yc.weixin.req.message;

import java.io.Serializable;

public class LinkMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7252998433902464667L;
	//消息标题
	private String title;
	//消息描述
	private String description;
	//消息链接
	private String url;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
