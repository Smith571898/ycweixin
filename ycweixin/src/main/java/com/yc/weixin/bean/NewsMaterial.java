package com.yc.weixin.bean;

import java.io.Serializable;

public class NewsMaterial implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8653553411210723236L;
	private String media_id;
	private Integer status;
	private String create_at;
	private String type;
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
