package com.yc.weixin.model;

import java.util.List;

public class MenuModel {
	//一级菜单数组，个数应为1~3个
	
	private List<ButtonModel> button;

	@Override
	public String toString() {
		return "MenuModel [button=" + button + "]";
	}

	public List<ButtonModel> getButton() {
		return button;
	}

	public void setButton(List<ButtonModel> button) {
		this.button = button;
	}
}
