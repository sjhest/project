package com.sjh.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于构建Json字符串返回到前台显示的EasyUITree的JavaBean
 * 一个具有Tree结构的JavaBean需要先将其内容存放到该对象中才能被前台的EasyUi正常读取
 * 
 * @author sunjiahui
 *
 */
public class EasyUiTree implements Serializable{

	private static final long serialVersionUID = 4950248146491796765L;
	private Integer id;
	private String text;
	private Map<String, Object> attributes = new HashMap<>();
    private String state = "open";
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}
