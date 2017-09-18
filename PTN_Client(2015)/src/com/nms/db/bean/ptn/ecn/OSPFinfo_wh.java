package com.nms.db.bean.ptn.ecn;

import java.io.Serializable;

import com.nms.ui.frame.ViewDataObj;

public class OSPFinfo_wh extends ViewDataObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1862747889441977977L;
	private int id;
	private int siteId;
	private int nodeType;//节点类型 0/1=端口/域
	private String ip;
	private String mask;//掩码
	private int portType;
	private int vlanValues;
	private int folw;
	private int portModel;
	private int enable;
	private int ospfType;//节点类型
	private boolean has = true;//是否是真是ospf
	
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
	public int getNodeType() {
		return nodeType;
	}
	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}
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
	public boolean isHas() {
		return has;
	}
	public void setHas(boolean has) {
		this.has = has;
	}
	
	
	@Override
	public void putObjectProperty() {
		
	}
	
	
	
}
