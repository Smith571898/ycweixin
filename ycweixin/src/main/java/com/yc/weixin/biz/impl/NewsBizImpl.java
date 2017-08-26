package com.yc.weixin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.weixin.bean.ArticleMaterial;
import com.yc.weixin.bean.NewsMaterial;
import com.yc.weixin.biz.NewsBiz;
import com.yc.weixin.dao.BaseDao;

@Transactional
@Service
public class NewsBizImpl implements NewsBiz {
	
	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public void addNews(NewsMaterial newsMaterial, List<ArticleMaterial> list) {
		baseDao.save(newsMaterial, "addNews");
		baseDao.save(ArticleMaterial.class, "addArticles", list);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<NewsMaterial> findNews() {
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<ArticleMaterial> findArticles() {
		return null;
	}

}
