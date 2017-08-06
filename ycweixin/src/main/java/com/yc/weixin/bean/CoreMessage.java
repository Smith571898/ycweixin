package com.yc.weixin.bean;

import java.io.Serializable;

public class CoreMessage implements Serializable {

	/**
	 * 请求消息核心类
	 */
	private static final long serialVersionUID = -2068751141533530881L;
	//微信加密签名
	private String signature;
	//时间戳
	private String timestamp;
	//随机数
	private String nonce;
	//随机字符串
	private String echostr;
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	@Override
	public String toString() {
		return "CoreMessage [signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce + ", echostr="
				+ echostr + "]";
	}
}
