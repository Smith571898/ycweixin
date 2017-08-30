package com.yc.weixin.bean;

import java.io.Serializable;
import java.util.List;

public class Menu  implements  Serializable{

	private static final long serialVersionUID = 2595053615520186188L;
	
	private Integer bid;
	private  Integer sbid;
	private String menutype;//菜单的类型  click  view
	private String name;//一级菜单的名字
	private String sub_name;
	private String menukey; 
	private String url;
	private Integer grade;
	
	
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getSbid() {
		return sbid;
	}

	public void setSbid(Integer sbid) {
		this.sbid = sbid;
	}

	private List<TwoMenu> twoMenuList;

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenukey() {
		return menukey;
	}

	public void setMenukey(String menukey) {
		this.menukey = menukey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TwoMenu> getTwoMenuList() {
		return twoMenuList;
	}

	public void setTwoMenuList(List<TwoMenu> twoMenuList) {
		this.twoMenuList = twoMenuList;
	}


	

	public Menu(Integer bid, Integer sbid, String menutype, String name, String sub_name, String menukey, String url,
			Integer grade, List<TwoMenu> twoMenuList) {
		super();
		this.bid = bid;
		this.sbid = sbid;
		this.menutype = menutype;
		this.name = name;
		this.sub_name = sub_name;
		this.menukey = menukey;
		this.url = url;
		this.grade = grade;
		this.twoMenuList = twoMenuList;
	}

	public Menu() {
		super();
	}

	@Override
	public String toString() {
		return "Menu [bid=" + bid + ", sbid=" + sbid + ", menutype=" + menutype + ", name=" + name + ", sub_name="
				+ sub_name + ", menukey=" + menukey + ", url=" + url + ", grade=" + grade + ", twoMenuList="
				+ twoMenuList + "]";
	}

	
	
	
	
}
