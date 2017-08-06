package com.yc.weixin.resp.message;

import java.io.Serializable;

public class ImageMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1843429644944371032L;
	//图片
	private Image image;
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
