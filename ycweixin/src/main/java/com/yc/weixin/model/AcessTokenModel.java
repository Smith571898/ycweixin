package com.yc.weixin.model;

public class AcessTokenModel {
	
	//token凭证
	private String access_token;
	//有效期
	private String expires_in;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	@Override
	public String toString() {
		return "AcessTokenModel [access_token=" + access_token + ", expires_in=" + expires_in + "]";
	}
	
	
}
