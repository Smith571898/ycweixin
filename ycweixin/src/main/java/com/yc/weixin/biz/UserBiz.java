package com.yc.weixin.biz;

import java.util.List;
import java.util.Map;

import com.yc.weixin.bean.WeChatUser;



public interface UserBiz {
	
	public List<WeChatUser> findAllUser(Map  map);
	
	public Integer findWeChatUserCount();
	
	public boolean AddUserInfo(List<WeChatUser> UserInfoList);
}
