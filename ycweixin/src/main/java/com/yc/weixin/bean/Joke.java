package com.yc.weixin.bean;

import java.io.Serializable;

//笑话表
public class Joke implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 812978349306106406L;

	//笑话id
	private Integer joke_id;
	//笑话内容
	private String joke_content;
	public Integer getJoke_id() {
		return joke_id;
	}
	public void setJoke_id(Integer joke_id) {
		this.joke_id = joke_id;
	}
	public String getJoke_content() {
		return joke_content;
	}
	public void setJoke_content(String joke_content) {
		this.joke_content = joke_content;
	}
	
}
