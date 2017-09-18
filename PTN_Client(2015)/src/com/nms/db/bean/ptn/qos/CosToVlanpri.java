package com.nms.db.bean.ptn.qos;

import com.nms.ui.frame.ViewDataObj;

@SuppressWarnings("serial")
public class CosToVlanpri extends ViewDataObj{
	private int id; //id主键
	private String name;//名
	private int BE; //BE
	private int AF1; //AF2
	private int AF2; //AF2
	private int AF3; //AF3
	private int AF4; //AF4
	private int EF; //EF
	private int CS6; //CS6
	private int CS7; //CS7
	
	QosMappingMode qosMappingMode = new QosMappingMode();
	
	@Override
	@SuppressWarnings("unchecked")
	public void putObjectProperty() {
		getClientProperties().put("id", getId());
		getClientProperties().put("name", getName());
		getClientProperties().put("BE", "VLANPRI"+getBE());
		getClientProperties().put("AF1", "VLANPRI"+getAF1());
		getClientProperties().put("AF2", "VLANPRI"+getAF2());
		getClientProperties().put("AF3", "VLANPRI"+getAF3());
		getClientProperties().put("AF4", "VLANPRI"+getAF4());
		getClientProperties().put("EF", "VLANPRI"+getEF());
		getClientProperties().put("CS6", "VLANPRI"+getCS6());
		getClientProperties().put("CS7", "VLANPRI"+getCS7());
		
		
	}
	public QosMappingMode getQosMappingMode() {
		return qosMappingMode;
	}
	public void setQosMappingMode(QosMappingMode qosMappingMode) {
		this.qosMappingMode = qosMappingMode;
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
	public int getBE() {
		return BE;
	}
	public void setBE(int bE) {
		BE = bE;
	}
	public int getAF1() {
		return AF1;
	}
	public void setAF1(int aF1) {
		AF1 = aF1;
	}
	public int getAF2() {
		return AF2;
	}
	public void setAF2(int aF2) {
		AF2 = aF2;
	}
	public int getAF3() {
		return AF3;
	}
	public void setAF3(int aF3) {
		AF3 = aF3;
	}
	public int getAF4() {
		return AF4;
	}
	public void setAF4(int aF4) {
		AF4 = aF4;
	}
	public int getEF() {
		return EF;
	}
	public void setEF(int eF) {
		EF = eF;
	}
	public int getCS6() {
		return CS6;
	}
	public void setCS6(int cS6) {
		CS6 = cS6;
	}
	public int getCS7() {
		return CS7;
	}
	public void setCS7(int cS7) {
		CS7 = cS7;
	}

}
