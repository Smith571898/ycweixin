package com.yc.weixin.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.weixin.bean.ArticleMaterial;
import com.yc.weixin.bean.FollowPushMessage;
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
	public List<NewsMaterial> findNews(Map map) {
		List<NewsMaterial> list = null;
		list = baseDao.findAll(NewsMaterial.class, "findAllNews", map);
		return list;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<ArticleMaterial> findArticles(String mediaId) {
		List<ArticleMaterial> list = null;
		Map<String,String> map = new HashMap<String,String>();
		map.put("media_id", mediaId);
		list = baseDao.findAll(ArticleMaterial.class, "findAllArticles", map);
		return list;
	}
	
	public Integer finNewsCount() {
		Integer total=(int) this.baseDao.getFunc(NewsMaterial.class, "findNewsCount");
		return total;
	}

}
