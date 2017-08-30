package com.yc.weixin.biz;

import java.util.List;
import java.util.Map;

import com.yc.weixin.bean.Chat_log;
import com.yc.weixin.bean.Joke;
import com.yc.weixin.bean.Knowledge;
import com.yc.weixin.bean.Knowledge_sub;

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
	
	
	/**
	 * 查看所有的一级知识表
	 * 
	 * 
	 */
	public List<Knowledge> findAllKnowLedge(Map map);
	
	public Integer getKnowLedgeCount();
	
	public void updateknowledge(Knowledge knowledge);
	/**
	 * 一级知识表的添加
	 * @param knowledge
	 */
	public  void addknowledge(Knowledge knowledge);
	
	/**
	 * 
	 * 笑话表的查询
	 */
	public  List<Joke> findAllJoke(Map map);
	
	/**
	 * 笑话表总数查询
	 */
	public  Integer getJokeCount();
	
	public List<Knowledge_sub>  findAllTwoKnowLedge(Map map);
	
	public  Integer getTwoKnowledgeCount();
	
	public void updatetwoknow(Knowledge_sub knowledge_sub);
	
	public void addtwoknow(Knowledge_sub knowledge_sub);
	
	public void updatejoke(Joke joke);
	
	public void  addjoke(Joke joke);
	
	public void delknow(Knowledge  knowledge);
	
	public void  deljoke(Joke joke);
	
	public void deltwoKnow(Knowledge_sub knowledge_sub);
}
