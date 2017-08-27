package com.yc.weixin.model;

public class ErrorModel {
	
	//错误码
	private Integer errcode;
	//错误信息
	private String errmsg;
	//媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
	private String type;
	//消息发送任务的ID
	private String msg_id;
	//消息的数据ID，，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
	private String msg_data_id;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public String getMsg_data_id() {
		return msg_data_id;
	}
	public void setMsg_data_id(String msg_data_id) {
		this.msg_data_id = msg_data_id;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	@Override
	public String toString() {
		return "ErrorModel [errcode=" + errcode + ", errmsg=" + errmsg + ", type=" + type + ", msg_id=" + msg_id
				+ ", msg_data_id=" + msg_data_id + "]";
	}
}
