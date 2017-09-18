package com.nms.drive.service.bean;


public class OSPFinfoWhObeject {
	private String ip;
	private String mask;//掩码
	private int portType;
	private int vlanValues;
	private int folw;
	private int portModel;
	private int enable;
	private int ospfType;//节点类型
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public int getPortType() {
		return portType;
	}
	public void setPortType(int portType) {
		this.portType = portType;
	}
	public int getVlanValues() {
		return vlanValues;
	}
	public void setVlanValues(int vlanValues) {
		this.vlanValues = vlanValues;
	}
	public int getFolw() {
		return folw;
	}
	public void setFolw(int folw) {
		this.folw = folw;
	}
	public int getPortModel() {
		return portModel;
	}
	public void setPortModel(int portModel) {
		this.portModel = portModel;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public int getOspfType() {
		return ospfType;
	}
	public void setOspfType(int ospfType) {
		this.ospfType = ospfType;
	}
	
}
