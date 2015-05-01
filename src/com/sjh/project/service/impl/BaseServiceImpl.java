package com.sjh.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sjh.project.service.BaseService;
import com.sjh.project.dao.BaseDao;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> dao;
	
	
	@Override
	public void saveEntity(T t) {
		dao.saveEntity(t);
		
	}

	@Override
	public void updateEntity(T t) {
		dao.updateEntity(t);

	}

	@Override
	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);

	}

	@Override
	public void deleteEntity(T t) {
		dao.deleteEntity(t);

	}

	@Override
	public void batchEntityByHql(String hql, Object... objects) {
		dao.batchEntityByHql(hql, objects);

	}

	@Override
	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHql(String hql, Object... objects) {
		return dao.findEntityByHql(hql, objects);
	}

}
