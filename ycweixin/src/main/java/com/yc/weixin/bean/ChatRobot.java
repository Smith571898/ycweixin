package com.yc.weixin.bean;

import java.io.Serializable;

public class ChatRobot  implements Serializable {

	private static final long serialVersionUID = 6394555244875162522L;
	private String nickname;
	private String create_time;
	private String req_msg;
	private String resp_msgfrom;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getResp_msgfrom() {
		return resp_msgfrom;
	}
	public void setResp_msgfrom(String resp_msgfrom) {
		this.resp_msgfrom = resp_msgfrom;
	}
	public ChatRobot(String nickname, String create_time, String req_msg, String resp_msgfrom) {
		super();
		this.nickname = nickname;
		this.create_time = create_time;
		this.req_msg = req_msg;
		this.resp_msgfrom = resp_msgfrom;
	}
	public ChatRobot() {
		super();
	}
	@Override
	public String toString() {
		return "ChatRobot [nickname=" + nickname + ", create_time=" + create_time + ", req_msg=" + req_msg
				+ ", resp_msgfrom=" + resp_msgfrom + "]";
	}
	
}
