package com.yc.weixin.bean;

import java.io.Serializable;

public class TwoMenu implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8278612572023741936L;

	private Integer sbid;
	private String name; //二级菜单的名字
	private String menutype;//菜单的类型  click  view
	private String menukey; 

	private String url;
	private  Integer grade;  //显示字段是第一个 还是第二个还是第三个 
	
	public TwoMenu(Integer sbid, String name, String menutype, String menukey, String url, Integer grade) {
		super();
		this.sbid = sbid;
		this.name = name;
		this.menutype = menutype;
		this.menukey = menukey;
		this.url = url;
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "TwoMenu [sbid=" + sbid + ", name=" + name + ", menutype=" + menutype + ", menukey=" + menukey + ", url="
				+ url + ", grade=" + grade + "]";
	}

	public Integer getSbid() {
		return sbid;
	}

	public void setSbid(Integer sbid) {
		this.sbid = sbid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
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

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public TwoMenu() {
		super();
	}
	
	
	
}
