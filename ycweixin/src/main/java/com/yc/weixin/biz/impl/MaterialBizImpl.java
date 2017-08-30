package com.yc.weixin.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.weixin.bean.FollowPushMessage;
import com.yc.weixin.bean.Material;
import com.yc.weixin.biz.MaterialBiz;
import com.yc.weixin.dao.BaseDao;

@Service
@Transactional
public class MaterialBizImpl implements MaterialBiz {
	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public void addMaterial(Material material) {
		baseDao.save(material, "addMaterial");
	}

	@Override
	public void delMaterial(Material material) {
		baseDao.del(material, "delMaterial");
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	@Override
	public List<Material> findMaterial(Map map) {
		List<Material> list = new ArrayList<Material>();
		list = baseDao.findAll(Material.class, "findAllMaterial", map);
		
		return list;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	@Override
	public Integer finMaterialCount() {
		Integer total=(int) this.baseDao.getFunc(Material.class, "findMaterialCount");
		return total;
	}

}
