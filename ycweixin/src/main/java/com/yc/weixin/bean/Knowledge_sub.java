package com.yc.weixin.bean;

import java.io.Serializable;

//问答知识子表
public class Knowledge_sub implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7388611134976110229L;

	//
	private Integer id;
	//与knowledge表中的id相对应
	private Integer pid;
	//答案
	private String subquestion;
	private String answer;
	
	public String getSubquestion() {
		return subquestion;
	}
	public void setSubquestion(String subquestion) {
		this.subquestion = subquestion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
}
