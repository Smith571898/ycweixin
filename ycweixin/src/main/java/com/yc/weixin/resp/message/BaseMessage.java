package com.yc.weixin.resp.message;

public abstract class BaseMessage {
	/**
	 * yhy
	 */
	//接收方账号(收到的OpenId)
	private String ToUserName;
	//开发者微信号
	private String FromUserName;
	//消息创建时间(整型)
	private long CreateTime;
	//消息类型(text/music/news)
	private String MsgType;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		this.CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		this.MsgType = msgType;
	}
}
