package com.nms.db.bean.ptn.qos;



import com.nms.ui.frame.ViewDataObj;

public class QosMappingAttr extends ViewDataObj {
	private static final long serialVersionUID = 1378536147088961058L;
	private int id;
	private int phbId;//映射模板主键id
	private String grade;//级别
	private String name;
	private int value;
	private int siteId;
	private int model;//模板类型
	private int direction;//方向
	private int color; //颜色
	private int groupid;
	private int mappingType;
	private int businessId;
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhbId() {
		return phbId;
	}
	public void setPhbId(int phbId) {
		this.phbId = phbId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getMappingType() {
		return mappingType;
	}
	public void setMappingType(int mappingType) {
		this.mappingType = mappingType;
	}
	@Override
	public void putObjectProperty() {
		
		
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	
}

