package com.yc.weixin.bean;

import java.io.Serializable;
import java.util.Arrays;

public class WeChatUser implements  Serializable {
	private String openid;
	private String nickname;
	private String headimgurl;
	private String sex;
	private long subscribe_time;
	private String subscribe;
	private String address;
	private String language;
	

	
	
	private String unionid;
	private String remark;
	private Integer groupid;
	private String[] tagid_list;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public long getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	public String[] getTagid_list() {
		return tagid_list;
	}
	public void setTagid_list(String[] tagid_list) {
		this.tagid_list = tagid_list;
	}
	@Override
	public String toString() {
		return "WeChatUser [openid=" + openid + ", nickname=" + nickname + ", headimgurl=" + headimgurl + ", sex=" + sex
				+ ", subscribe_time=" + subscribe_time + ", subscribe=" + subscribe + ", address=" + address
				+ ", language=" + language + ", unionid=" + unionid + ", remark=" + remark + ", groupid=" + groupid
				+ ", tagid_list=" + Arrays.toString(tagid_list) + "]";
	}
	public WeChatUser(String openid, String nickname, String headimgurl, String sex, long subscribe_time,
			String subscribe, String address, String language, String unionid, String remark, Integer groupid,
			String[] tagid_list) {
		super();
		this.openid = openid;
		this.nickname = nickname;
		this.headimgurl = headimgurl;
		this.sex = sex;
		this.subscribe_time = subscribe_time;
		this.subscribe = subscribe;
		this.address = address;
		this.language = language;
		this.unionid = unionid;
		this.remark = remark;
		this.groupid = groupid;
		this.tagid_list = tagid_list;
	}
	public WeChatUser() {
		super();
	}

	
}
