package com.yc.weixin.bean;

import java.io.Serializable;

public class Material implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8546387915481204233L;
	private String media_id;
	private String mpurl;
	private String url;
	private String type;
	private Long create_at;
	public Long getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Long create_at) {
		this.create_at = create_at;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getMpurl() {
		return mpurl;
	}
	public void setMpurl(String mpurl) {
		this.mpurl = mpurl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
