package com.yc.weixin.req.message;

import java.io.Serializable;

public class VideoMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8507155413204760963L;
	//视频消息媒体Id
	private String mediaId;
	//视频消息缩略图的媒体Id
	private String thumbMediaId;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
}
