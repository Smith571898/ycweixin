package com.yc.weixin.req.message;

import java.io.Serializable;

public class ImageMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010813859984141814L;
	//图片链接
	private String picUrl;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
