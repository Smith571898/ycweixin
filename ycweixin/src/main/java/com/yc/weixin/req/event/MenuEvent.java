package com.yc.weixin.req.event;

import java.io.Serializable;

public class MenuEvent extends BaseEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3703263597149746540L;
	//事件Key值,与自定义菜单接口中key值对应
	private String eventKey;
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
