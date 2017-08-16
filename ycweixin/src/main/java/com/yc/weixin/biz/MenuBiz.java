package com.yc.weixin.biz;

import java.util.List;
import java.util.Map;

import com.yc.weixin.bean.Menu;

public interface MenuBiz {
	/**
	 * 
	 * 寻找所有的二级菜单
	 * @param map
	 * @return
	 */
	public List<Menu>  findAllMenu(Map  map);
	/**
	 * 得到所有的二级菜单总数
	 * @return
	 */
	public Integer getMenuCount();
	/**
	 * 更新二级菜单
	 * @param menu
	 */
	public void updateMenu(Menu menu);
	/**
	 * 
	 * 更新一级菜单
	 * @param menu
	 */
	public void updateOneMenu(Menu menu);
	/**
	 * 
	 * 查看所有的一级菜单
	 * @param map
	 * @return
	 */
	public List<Menu> findAllOneMenu(Map map);
	/**
	 * 
	 * 得到一级菜单的总数
	 * @return
	 */
	public Integer getMenuOneCount();
	/**
	 * 
	 * 添加二级菜单
	 * @param menu
	 */
	public void AddTwomenu(Menu menu);
	/**
	 * 添加一级菜单
	 * @param menu
	 */
	public void AddOnemenu(Menu menu);
	
	public List<Menu> findOrderMenu(Map  map);
}
