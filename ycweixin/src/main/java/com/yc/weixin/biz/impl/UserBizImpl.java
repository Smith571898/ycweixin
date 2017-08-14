package com.yc.weixin.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.weixin.bean.WeChatUser;
import com.yc.weixin.biz.UserBiz;
import com.yc.weixin.dao.BaseDao;

@Service
public class UserBizImpl  implements UserBiz{
	@Resource(name="baseDao")
	private BaseDao  baseDao;
	
	public List<WeChatUser> findAllUser(Map map) {
		List<WeChatUser> list=this.baseDao.findAll(WeChatUser.class, "findAllUserInfo", map);
		return list;
	}

	@Override
	public Integer findWeChatUserCount() {
		Integer total=(int) this.baseDao.getFunc(WeChatUser.class, "findWeChatUserCount");
		return total;
	}

	@Override
	public boolean AddUserInfo(List<WeChatUser> UserInfoList) {
		if(this.baseDao.save(WeChatUser.class, "addUserInfoTogether", UserInfoList)){
			return true;
		}
		return false;
	}


	
	

}
