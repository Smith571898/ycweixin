package com.yc.weixin.biz;

import java.util.List;
import java.util.Map;

import com.yc.weixin.bean.ArticleMaterial;
import com.yc.weixin.bean.NewsMaterial;

public interface NewsBiz {
	
	public void addNews(NewsMaterial newsMaterial,List<ArticleMaterial> list);
	
	public List<NewsMaterial> findNews(Map map);
	
	public List<ArticleMaterial> findArticles(String mediaId);
	
	public Integer finNewsCount();
}
