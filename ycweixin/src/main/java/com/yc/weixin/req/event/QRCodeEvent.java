package com.yc.weixin.req.event;

import java.io.Serializable;

public class QRCodeEvent extends BaseEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5011716648251787353L;
	//事件key值(SCENE_VALUE,qrscene_123123)
	private String eventKey;
	//用于换取二维码图片
	private String ticket;
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
}
