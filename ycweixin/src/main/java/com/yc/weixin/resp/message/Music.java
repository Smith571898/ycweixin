package com.yc.weixin.resp.message;

public class Music {
	//音乐标题
	private String title;
	//音乐描述
	private String description;
	//音乐链接
	private String musicUrl;
	//高质量音乐链接,Wi-Fi环境优先使用该链接播放音乐
	private String hqMusciUrl;
	//缩略图的媒体Id,通过上传多媒体文件得到的Id
	private String thumbMediaId;
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
	public String getMusicUrl() {
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public String getHqMusciUrl() {
		return hqMusciUrl;
	}
	public void setHqMusciUrl(String hqMusciUrl) {
		this.hqMusciUrl = hqMusciUrl;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
}
