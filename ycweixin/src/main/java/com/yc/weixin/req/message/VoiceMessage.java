package com.yc.weixin.req.message;

import java.io.Serializable;

public class VoiceMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2261852668383448094L;
	//媒体id
	private String mediaId;
	//语音格式
	private String format;
	//语音识别结果,UTF8编码
	private String recognition;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getRecognition() {
		return recognition;
	}
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
}
