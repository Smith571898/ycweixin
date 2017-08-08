package com.yc.weixin.bean;

import java.io.Serializable;

public class WeChatUser implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3984670902101871689L;
	
	private  Integer uid;
	private String head ;
	private String nikname ;
	private String 	sex     ;
 	private String address  ;
 	private String 	followtime  ;
	private String isfollow   ;
	private String name    ;
	private String telephone  ;
	private String schoolname ;
	private String 	nowclass   ;
	private String 	ycclass   ;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getNikname() {
		return nikname;
	}
	public void setNikname(String nikname) {
		this.nikname = nikname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFollowtime() {
		return followtime;
	}
	public void setFollowtime(String followtime) {
		this.followtime = followtime;
	}
	public String getIsfollow() {
		return isfollow;
	}
	public void setIsfollow(String isfollow) {
		this.isfollow = isfollow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public String getNowclass() {
		return nowclass;
	}
	public void setNowclass(String nowclass) {
		this.nowclass = nowclass;
	}
	public String getYcclass() {
		return ycclass;
	}
	public void setYcclass(String ycclass) {
		this.ycclass = ycclass;
	}
	@Override
	public String toString() {
		return "WeChatUser [uid=" + uid + ", head=" + head + ", nikname=" + nikname + ", sex=" + sex + ", address="
				+ address + ", followtime=" + followtime + ", isfollow=" + isfollow + ", name=" + name + ", telephone="
				+ telephone + ", schoolname=" + schoolname + ", nowclass=" + nowclass + ", ycclass=" + ycclass + "]";
	}
	public WeChatUser(Integer uid, String head, String nikname, String sex, String address, String followtime,
			String isfollow, String name, String telephone, String schoolname, String nowclass, String ycclass) {
		super();
		this.uid = uid;
		this.head = head;
		this.nikname = nikname;
		this.sex = sex;
		this.address = address;
		this.followtime = followtime;
		this.isfollow = isfollow;
		this.name = name;
		this.telephone = telephone;
		this.schoolname = schoolname;
		this.nowclass = nowclass;
		this.ycclass = ycclass;
	}
	public WeChatUser() {
		super();
	}
	
	
}
