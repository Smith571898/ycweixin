package com.yc.weixin.model;

public class UserModel {
	//是否关注
	private Integer subscribe;
	private String openid;
	//昵称
	private String nickname;
	//性别 
	private Integer sex;
	//语言
	private String language;
	//省
	private String province;
	//国家
	private String country;
	//头像地址
	private String headimgurl;
	//关注时间
	private long subscirbe_time;
	//多个公众号的联合id
	private String unionid;
	//备注
	private String remark;
	//分组id
	private Integer groupid;
	//标签列表
	private String[] tagid_list;
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public long getSubscirbe_time() {
		return subscirbe_time;
	}
	public void setSubscirbe_time(long subscirbe_time) {
		this.subscirbe_time = subscirbe_time;
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
		return "UserModel [subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex
				+ ", language=" + language + ", province=" + province + ", country=" + country + ", headimgurl="
				+ headimgurl + ", subscirbe_time=" + subscirbe_time + ", unionid=" + unionid + ", remark=" + remark
				+ ", groupid=" + groupid + ", tagid_list=" + tagid_list + "]";
	}
}
