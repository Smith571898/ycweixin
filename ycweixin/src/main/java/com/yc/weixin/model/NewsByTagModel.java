package com.yc.weixin.model;

public class NewsByTagModel {
	//用于设定图文消息的接收者
	private TagModel filter;
	//用于设定即将发送的图文消息
	private MPNewsModel mpnews;
	//群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
	private String msgtype;
	//1为继续群发（转载），0为停止群发。 该参数默认为0。
	private Integer send_ignore_reprint;
	public TagModel getFilter() {
		return filter;
	}
	public void setFilter(TagModel filter) {
		this.filter = filter;
	}
	public MPNewsModel getMpnews() {
		return mpnews;
	}
	public void setMpnews(MPNewsModel mpnews) {
		this.mpnews = mpnews;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public Integer getSend_ignore_reprint() {
		return send_ignore_reprint;
	}
	public void setSend_ignore_reprint(Integer send_ignore_reprint) {
		this.send_ignore_reprint = send_ignore_reprint;
	}
}
