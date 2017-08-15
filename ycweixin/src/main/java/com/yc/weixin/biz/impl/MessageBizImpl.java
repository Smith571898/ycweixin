package com.yc.weixin.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.weixin.bean.FollowPushMessage;
import com.yc.weixin.biz.MessageBiz;
import com.yc.weixin.dao.BaseDao;
@Service
public class MessageBizImpl implements MessageBiz {
		@Resource(name="baseDao")
		private BaseDao baseDao;
	@Override
	
	public List<FollowPushMessage> findFollowPushMessage(Map map) {
		List<FollowPushMessage> list=this.baseDao.findAll(FollowPushMessage.class, "findAllFollowPush", map);
		return list;
	}
	
	@Override
	public Integer finFollowPushCount() {
		Integer total=(int) this.baseDao.getFunc(FollowPushMessage.class, "findFollowPushCount");
		return total;
	}
	
	@Override
	public boolean updateFollowPush(FollowPushMessage fpm) {
		this.baseDao.save(fpm, "updateFollowPush");
		return true;
	}


}
