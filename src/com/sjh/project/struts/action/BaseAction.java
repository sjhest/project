package com.sjh.project.struts.action;

import java.lang.reflect.ParameterizedType;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>, Preparable{

	private static final long serialVersionUID = 5563024734375026320L;
	
	protected T model;
	
	public BaseAction(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz = (Class<T>) type.getActualTypeArguments()[0];
		try {
			model = (T)clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void prepare() throws Exception {
		
	}

	@Override
	public T getModel(){
		return model;
	}
}
