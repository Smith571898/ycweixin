package com.yc.weixin.biz;

import java.util.List;

import com.yc.weixin.bean.ArticleMaterial;
import com.yc.weixin.bean.NewsMaterial;

public interface NewsBiz {
	
	public void addNews(NewsMaterial newsMaterial,List<ArticleMaterial> list);
	
	public List<NewsMaterial> findNews();
	
	public List<ArticleMaterial> findArticles();
	
}
