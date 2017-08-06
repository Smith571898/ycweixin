package com.yc.weixin.resp.message;

import java.io.Serializable;

public class MusicMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3308069479397165576L;
	//音乐
	private Music music;
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
}
