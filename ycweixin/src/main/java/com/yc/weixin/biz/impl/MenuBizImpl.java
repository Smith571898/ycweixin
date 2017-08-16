package com.yc.weixin.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.weixin.bean.Menu;
import com.yc.weixin.bean.TwoMenu;
import com.yc.weixin.biz.MenuBiz;
import com.yc.weixin.dao.BaseDao;

@Service
public class MenuBizImpl  implements MenuBiz{
	@Resource(name="baseDao")
	private BaseDao baseDao;

	public List<Menu> findAllMenu(Map map) {
		List<Menu>  list=this.baseDao.findAll(Menu.class, "findAllmenu", map);
		return list;
	}

	@Override
	public Integer getMenuCount() {
		Integer count=this.baseDao.getFunc(Menu.class, "getMenuCount");
		return count;
	}

	@Override
	public void updateMenu(Menu menu) {
		this.baseDao.update(menu, "updateMenu");
	
	}

	@Override
	public List<Menu> findAllOneMenu(Map map) {
		List<Menu>  list=this.baseDao.findAll(Menu.class, "findAllOnemenu", map);
		return list;
	}

	@Override
	public Integer getMenuOneCount() {
		Integer count=this.baseDao.getFunc(Menu.class, "getOneMenuCount");
		return count;
	}

	@Override
	public void AddTwomenu(Menu menu) {
		this.baseDao.save(menu, "addTwoGradeMenu");
		
	}

	@Override
	public void AddOnemenu(Menu menu) {
		this.baseDao.save(menu, "addOneGradeMenu");
		
	}

	@Override
	public void updateOneMenu(Menu menu) {
		this.baseDao.update(menu, "updateOneMenu");
		
	}
/**
 * 
 * 查二级菜单
 */
	@Override
	public List<Menu> findOrderMenu(Map map) {
		List<Menu> list=this.baseDao.findAll(Menu.class, "findOrderMenu", map);
		
		return list;
	}
	
	
	
	
}
