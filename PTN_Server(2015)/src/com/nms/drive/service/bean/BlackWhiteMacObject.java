package com.nms.drive.service.bean;

/**
 * 适配对象
 * @author zk
 */
public class BlackWhiteMacObject {

	private int vsId = 0;//Vsid elan的业务ID
	private int nameList = 1;//名单模式：1/2=黑名单/白名单模式 
	private String macAddress = "00-00-00-00-00-00" ;//MAC地址：默认00-00-00-00-00-00
	
	public int getVsId() {
		return vsId;
	}
	public void setVsId(int vsId) {
		this.vsId = vsId;
	}
	public int getNameList() {
		return nameList;
	}
	public void setNameList(int nameList) {
		this.nameList = nameList;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	
}
