package com.yc.weixin.model;

import java.util.List;

public class NewsMaterialModel {

	//单条图文消息的集合
	private List<ArticleMaterialModel> articles;

	public List<ArticleMaterialModel> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleMaterialModel> articles) {
		this.articles = articles;
	}
}
