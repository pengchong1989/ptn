package com.nms.db.bean.equipment.port;

import com.nms.ui.frame.ViewDataObj;

public class PortChildAttr extends ViewDataObj {
	private static final long serialVersionUID = 6429030908961440275L;
	private int id;
	private int siteId;
	private int type;   //pw端口流和或者Ac
	private String name;
	private int portId;  //--承载的端口
	private int TagRecognition ;  //--(端口)TAG识别(0)/1=(不识别)/识别
	private int TagBehavior  ;  //--(端口)TAG行为(0)/1/2=(NOP)/增加/删除
	private int TagValnId  ;   //(端口)增加VLAN ID 
	private int TagValnPri  ;   //(端口)增加VLAN PRI
	private int macAddresslearn  ;  //(端口)MAC地址学习0/(1)=禁止/(使能) 
	private int portSplitHorizon   ;  //  (端口)水平分割(0)/1=(关)/开
	
	private int isused; //是否被其他所用
	private int controlEnabl =1;//控制字使能
	
	
	public int getControlEnabl() {
		return controlEnabl;
	}
	public void setControlEnabl(int controlEnabl) {
		this.controlEnabl = controlEnabl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	public int getPortId() {
		return portId;
	}
	public void setPortId(int portId) {
		this.portId = portId;
	}
	
	public int getTagRecognition() {
		return TagRecognition;
	}
	public void setTagRecognition(int tagRecognition) {
		TagRecognition = tagRecognition;
	}
	public int getTagBehavior() {
		return TagBehavior;
	}
	public void setTagBehavior(int tagBehavior) {
		TagBehavior = tagBehavior;
	}
	public int getTagValnId() {
		return TagValnId;
	}
	public void setTagValnId(int tagValnId) {
		TagValnId = tagValnId;
	}
	public int getTagValnPri() {
		return TagValnPri;
	}
	public void setTagValnPri(int tagValnPri) {
		TagValnPri = tagValnPri;
	}
	public int getMacAddresslearn() {
		return macAddresslearn;
	}
	public void setMacAddresslearn(int macAddresslearn) {
		this.macAddresslearn = macAddresslearn;
	}
	public int getPortSplitHorizon() {
		return portSplitHorizon;
	}
	public void setPortSplitHorizon(int portSplitHorizon) {
		this.portSplitHorizon = portSplitHorizon;
	}
	public int getIsused() {
		return isused;
	}
	public void setIsused(int isused) {
		this.isused = isused;
	}
	@Override
	public void putObjectProperty() {
		 
		
	}
	
}
