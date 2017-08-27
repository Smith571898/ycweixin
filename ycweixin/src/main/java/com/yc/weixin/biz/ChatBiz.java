package com.yc.weixin.biz;

import java.util.List;
import java.util.Map;

import com.yc.weixin.bean.Chat_log;
import com.yc.weixin.bean.Knowledge;

public interface ChatBiz {
	
	/**
	 * 得到索引存储目录
	 * 
	 * @return WEB-INF/classes/index/
	 */
	public String getIndexDir();
	
	/**
	 * 创建索引
	 */
	public void createIndex();
	
	/**
	 * 聊天方法（根据question返回answer）
	 * 
	 * @param openId 用户的OpenID
	 * @param createTime 消息创建时间
	 * @param question 用户上行的问题
	 * @return answer
	 */
	public String chat(String openId, String createTime, String question);
	
	
	public List<Chat_log>  findAllChat_Log(Map map);

	public Integer getChatLogCount();
	
	public List<Chat_log>  findChatLogByDate(Map map);
	
	public Integer getChatLogCountByDate(Map map);
	
}
