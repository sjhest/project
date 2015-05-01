package com.sjh.project.service;

import java.util.List;

public interface BaseService<T> {

	//写操作
	public void saveEntity(T t);
	public void updateEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void deleteEntity(T t);
	//根据hql来批处理
	public void batchEntityByHql(String hql, Object...objects );
	
	//读操作
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHql(String hql, Object...objects);
}
