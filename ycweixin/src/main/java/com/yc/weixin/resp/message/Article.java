package com.yc.weixin.resp.message;

public class Article {
	//图文消息名称
	private String Title;
	//图文消息描述
	private String Description;
	//图片链接,支持jpg、png格式，较好的效果为大图640px X 320px ，小图80px X 80px
	private String PicUrl;
	//点击图文消息跳转链接
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String Title) {
		this.Title = Title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String PicUrl) {
		this.PicUrl = PicUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String Url) {
		this.Url = Url;
	}
}
