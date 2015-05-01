package com.sjh.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Geography information 地理位置信息
 * 普通字段：id，name，type（国家、省、市、区），remark（特殊标记，比如珠三角、京津冀等等）
 * 关联字段parentGi（父节点）
 * @author sunjiahui
 *
 */
@Entity
@Table(name="GEOGRAPHY_INFOMATION")
public class Gi implements Serializable{


	private static final long serialVersionUID = -8963234506891567177L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	@Column(name="NAME")
	private String text;
	@Column(name="TYPE")
	private String type;
	@Column(name="CODE")
	private String code;
	@Column(name="REMARK")
	private String remark;
	private double longitude;
	private double latitude;

	@ManyToOne
	@JoinColumn(name="PARAENT_ID")
	private Gi parentGi;
	
	@OneToMany(mappedBy="parentGi", fetch=FetchType.LAZY)
	private Set<Gi> children = new HashSet<>();
	

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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Gi getParentGi() {
		return parentGi;
	}
	public void setParentGi(Gi parentGi) {
		this.parentGi = parentGi;
	}
	public Set<Gi> getChildrenGi() {
		return children;
	}
	public void setChildrenGi(Set<Gi> childrenGi) {
		this.children = childrenGi;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
}
