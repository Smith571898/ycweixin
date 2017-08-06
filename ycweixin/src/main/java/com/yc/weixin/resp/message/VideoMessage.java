package com.yc.weixin.resp.message;

import java.io.Serializable;

public class VideoMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3456320861411725304L;
	//视频
	private Video video;
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	
}
