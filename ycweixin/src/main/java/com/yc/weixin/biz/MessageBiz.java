package com.yc.weixin.biz;

import java.util.List;
import java.util.Map;

import com.yc.weixin.bean.FollowPushMessage;



public interface MessageBiz {
	public List<FollowPushMessage> findFollowPushMessage(Map map);

	public Integer finFollowPushCount();

	public boolean updateFollowPush(FollowPushMessage fpm);
	
	

	
}
