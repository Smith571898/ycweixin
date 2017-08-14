package com.yc.weixin.bean;

import java.io.Serializable;

/**
 * �ʴ�֪ʶmodel
 * 
 * @author 
 * @date 2013-12-01
 */
//问答知识表
public class Knowledge implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5719556478410614939L;
	private int id;
	//问题
	private String question;
	//答案
	private String answer;
	//知识的类别（1:普通对话 2:笑话 3:上下文）
	private int category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
