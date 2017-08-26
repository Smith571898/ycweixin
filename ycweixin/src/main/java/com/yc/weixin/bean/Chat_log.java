package com.yc.weixin.bean;

import java.io.Serializable;

//聊天记录表
public class Chat_log implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8904256562687519450L;
	
	private Integer id;
	//用户的OpenID
	private String open_id;
	//open id所对应的昵称
	private String nickname;
	
	//消息创建时间
	private String create_time;
	//用户上行的消息
	private String req_msg;
	public Chat_log() {
		super();
	}
	public Chat_log(Integer id, String open_id, String nickname, String create_time, String req_msg, String resp_msg,
			Integer chat_category) {
		super();
		this.id = id;
		this.open_id = open_id;
		this.nickname = nickname;
		this.create_time = create_time;
		this.req_msg = req_msg;
		this.resp_msg = resp_msg;
		this.chat_category = chat_category;
	}
	//公众账号回复的消息
	private String resp_msg;
	//聊天类别
	private Integer chat_category;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getReq_msg() {
		return req_msg;
	}
	public void setReq_msg(String req_msg) {
		this.req_msg = req_msg;
	}
	public String getResp_msg() {
		return resp_msg;
	}
	public void setResp_msg(String resp_msg) {
		this.resp_msg = resp_msg;
	}
	public Integer getChat_category() {
		return chat_category;
	}
	public void setChat_category(Integer chat_category) {
		this.chat_category = chat_category;
	}
	
	
}
