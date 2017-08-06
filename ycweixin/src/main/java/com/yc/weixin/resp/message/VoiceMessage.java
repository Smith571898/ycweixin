package com.yc.weixin.resp.message;

import java.io.Serializable;

public class VoiceMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8804747090007522032L;
	//语音
	private Voice voice;
	public Voice getVoice() {
		return voice;
	}
	public void setVoice(Voice voice) {
		this.voice = voice;
	}
}
