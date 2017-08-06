package com.yc.weixin.req.message;

public abstract class BaseMessage {

	/**
	 * yhy
	 */
	//开发者微信号
	private String toUserName;
	//发送方账号(一个OpenId)
	private String fromUserName;
	//消息创建时间(整型)
	private long createTime;
	//消息类型(text/image/location/link/voice)
	private String msgType;
	//消息Id,64位整形
	private long msgId;
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	

}
