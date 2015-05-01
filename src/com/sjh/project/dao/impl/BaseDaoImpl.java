package com.sjh.project.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.sjh.project.dao.BaseDao;

/**
 * 
 * @author sunjiahui
 * @BaseDaoImpl<T>抽象类，仅用于继承
 * 提供了SessionFactory对象
 * @param <T>
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
	/**
	 * 获取参数话泛型的实参
	 * 在Hibernate的get/load方法中，需要提供返回对象的返回类型
	 * 但是在现在这个抽象类中，没有办法得到返回对象，因此必须使用参数化泛型在运行时得到返回对象的类型
	 * 比如获取一个User对象时，在BaseDaoImpl<T>的子类UserDaoImpl<User>继承了getEntity方法
	 * 在调用UserDaoImpl的constructor时必须先调用父类构造器，因此在重写父类构造器方法
	 * 在该方法中，this代表UserDaoImpl实例（因为Abstract类无法实例化）
	 * 然后剩下的方法就去看反射的api吧
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	@Override
	public void saveEntity(T t) {
		sessionFactory.getCurrentSession().save(t);

	}

	@Override
	public void updateEntity(T t) {
		sessionFactory.getCurrentSession().update(t);

	}

	@Override
	public void saveOrUpdateEntity(T t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);

	}

	@Override
	public void deleteEntity(T t) {
		sessionFactory.getCurrentSession().delete(t);

	}

	/**
	 * 按照hql语句进行批量更新
	 */
	@Override
	public void batchEntityByHql(String hql, Object... objects) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for(int i=0; i<objects.length; i++){
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T loadEntity(Integer id) {
		return (T) sessionFactory.getCurrentSession().load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntity(Integer id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findEntityByHql(String hql, Object... objects) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for(int i=0; i<objects.length; i++){
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

}
