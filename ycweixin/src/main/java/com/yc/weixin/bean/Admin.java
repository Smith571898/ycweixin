package com.yc.weixin.bean;

import java.io.Serializable;

public class Admin implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2789027481778637709L;

	private String adminname;
	
	private String adminpwd;

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getAdminpwd() {
		return adminpwd;
	}

	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}

	@Override
	public String toString() {
		return "Admin [adminname=" + adminname + ", adminpwd=" + adminpwd + "]";
	}

	public Admin(String adminname, String adminpwd) {
		super();
		this.adminname = adminname;
		this.adminpwd = adminpwd;
	}

	public Admin() {
		super();
	}
	
	
	
}
