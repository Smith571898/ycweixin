package com.yc.weixin.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ShowMenu implements Serializable {

	private static final long serialVersionUID = 2526226342164735880L;

	private String name;
	private Map<String, Map<String, String>> twomenu;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Map<String, String>> getTwomenu() {
		return twomenu;
	}

	public void setTwomenu(Map<String, Map<String, String>> twomenu) {
		this.twomenu = twomenu;
	}

}
