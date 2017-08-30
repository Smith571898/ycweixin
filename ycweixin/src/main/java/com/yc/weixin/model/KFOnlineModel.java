package com.yc.weixin.model;

public class KFOnlineModel {

	//完整客服帐号，格式为：帐号前缀@公众号微信号
	private String kf_account;
	//客服在线状态，目前为：1、web 在线
	private Integer status;
	//客服编号
	private String kf_id;
	//客服当前正在接待的会话数
	private String accepted_case;
	public String getKf_account() {
		return kf_account;
	}
	public void setKf_account(String kf_account) {
		this.kf_account = kf_account;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getKf_id() {
		return kf_id;
	}
	public void setKf_id(String kf_id) {
		this.kf_id = kf_id;
	}
	public String getAccepted_case() {
		return accepted_case;
	}
	public void setAccepted_case(String accepted_case) {
		this.accepted_case = accepted_case;
	}
}
