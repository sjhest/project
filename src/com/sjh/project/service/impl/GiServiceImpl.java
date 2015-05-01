package com.sjh.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjh.project.dao.BaseDao;
import com.sjh.project.model.Gi;
import com.sjh.project.service.GiService;

@Service("giService")
public class GiServiceImpl extends BaseServiceImpl<Gi> implements GiService {

	@Autowired
	private BaseDao<Gi> giDao;

	@Override
	public List<Gi> getGiTree(Integer id) {
		String hql;
		if(id == null || id == 0){
			hql = "from Gi g where g.parentGi is null";
			return giDao.findEntityByHql(hql);
		}else{
			hql = "from Gi g where g.parentGi.id is ?";
			return giDao.findEntityByHql(hql, id);
		}	
	}

	@Override
	public int giChildrenCount(int id) {
		if(id == 0) return 0;
		return giDao.getEntity(id).getChildrenGi().size();
	}


	@Override
	public Gi appendGi(Gi model, Integer parentId) {
		if(parentId != null){
			Gi parentGi = giDao.getEntity(parentId);
			model.setParentGi(parentGi);
		}
		giDao.saveEntity(model);
		return model;
	}

	@Override
	public void removeNodeWithChildren(Gi model) {
		String hql = "delete Gi g where g.parentGi.id = ?";
		giDao.batchEntityByHql(hql, model.getId());
		giDao.deleteEntity(model);
	}

	

}
