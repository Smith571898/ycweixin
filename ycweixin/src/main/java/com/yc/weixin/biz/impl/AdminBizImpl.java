package com.yc.weixin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.weixin.bean.Admin;
import com.yc.weixin.biz.AdminBiz;
import com.yc.weixin.dao.BaseDao;
@Service
public class AdminBizImpl  implements  AdminBiz {
	@Resource(name="baseDao")
	private BaseDao baseDao;
	
	public Admin AdminLogin(Admin admin) {
		System.out.println(1);
		Admin admin2 = (Admin) this.baseDao.findOne(admin, "AdminLogin");
		return admin2;
	}

}
