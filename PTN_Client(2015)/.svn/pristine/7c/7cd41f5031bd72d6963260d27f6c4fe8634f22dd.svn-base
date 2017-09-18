package com.nms.db.bean.ptn.qos;


import java.util.List;

import com.nms.ui.frame.ViewDataObj;

public class QosMappingMode extends ViewDataObj{
	private static final long serialVersionUID = -7131420113275344833L;
	private int id; //id主键
	private String tableName;//表名，log日志用到
	private String name;//表名
	private int type; //模板类型
	private int siteId;// 网元ID
	private int portId; //端口ID
	private List<QosMappingAttr> qosMappingAttrList; //模板参数
	private String typeName;//模板类型名称
	private int businessId;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public List<QosMappingAttr> getQosMappingAttrList() {
		return qosMappingAttrList;
	}
	public void setQosMappingAttrList(List<QosMappingAttr> qosMappingAttrList) {
		this.qosMappingAttrList = qosMappingAttrList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		
		getClientProperties().put("id", getId());
		getClientProperties().put("name", getName());
		getClientProperties().put("BE", getValue("BE"));
		getClientProperties().put("AF1", getValue("AF1"));
		getClientProperties().put("AF2", getValue("AF2"));
		getClientProperties().put("AF3", getValue("AF3"));
		getClientProperties().put("AF4", getValue("AF4"));
		getClientProperties().put("EF", getValue("EF"));
		getClientProperties().put("CS6", getValue("CS6"));
		getClientProperties().put("CS7", getValue("CS7"));
		
	}
	
	/**
	 * 拼接界面显示值
	 * @param type
	 * @return
	 */
	public String getValue(String type){
		StringBuffer value = new StringBuffer();
		for(QosMappingAttr qosMappingAttr :getQosMappingAttrList()){
			if(type.equals(qosMappingAttr.getGrade())){
				if("TMC".equals(qosMappingAttr.getName())){
					value.append(qosMappingAttr.getValue()+"/");
				}else{
					value.append(qosMappingAttr.getValue());
				}
			}
		}
		return value.toString();
	}
	public int getPortId() {
		return portId;
	}
	public void setPortId(int portId) {
		this.portId = portId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
}
