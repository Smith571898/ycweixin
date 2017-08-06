package com.yc.weixin.resp.message;

public class Video {
	//媒体文件Id
	private String getMediaId;
	//缩略图的媒体ID
	private String thumbMediaId;
	public String getGetMediaId() {
		return getMediaId;
	}
	public void setGetMediaId(String getMediaId) {
		this.getMediaId = getMediaId;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
}
