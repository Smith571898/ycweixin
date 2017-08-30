package com.yc.weixin.model;

public class UserGroupModel {
	//当前关注公众号的人数
	private Integer total;
	//当前获取到的人数
	private Integer count;
	//下一次获取用户分组需要的openid
	private String next_openid;
	//openid的集合
	private DataFromUserGroupModel data;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	public DataFromUserGroupModel getData() {
		return data;
	}
	public void setData(DataFromUserGroupModel data) {
		this.data = data;
	}
}
