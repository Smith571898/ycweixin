package com.yc.weixin.biz;

import java.util.List;
import java.util.Map;

import com.yc.weixin.bean.Material;

public interface MaterialBiz {
	
	//新增素材
	public void addMaterial(Material material);
	
	//删除素材
	public void delMaterial(Material material);
	
	//查询素材
	public List<Material> findMaterial(Map map);
	
	//查询素材数量
	public Integer finMaterialCount();
}
