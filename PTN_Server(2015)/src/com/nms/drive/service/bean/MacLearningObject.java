package com.nms.drive.service.bean;

public class MacLearningObject {
	private int id;
	private int portId;
	private int macModel;//MAC学习限制模式：0/1/2=无限制/基于端口 /基于端口+VLAN(默认0)
	private int blackListEnabled;//黑名单使能：0/1=不使能/使能（默认0）
//	private int vlanId;//Vlan值：1~4094（默认1）
	private int macCount;//MAC地址学习限制数目：（0~255）（默认0）
	private int action;//黑名单无操作/删除/增加/全部删除 = 0/1/2/3（默认0）
	private int associateVlan;//黑名单关联vlan值：1~4094（默认1）
	private String mac;//黑名单MAC：（最多50个）（默认00-00-00-00-00-00）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPortId() {
		return portId;
	}
	public void setPortId(int portId) {
		this.portId = portId;
	}
	public int getMacModel() {
		return macModel;
	}
	public void setMacModel(int macModel) {
		this.macModel = macModel;
	}
	public int getBlackListEnabled() {
		return blackListEnabled;
	}
	public void setBlackListEnabled(int blackListEnabled) {
		this.blackListEnabled = blackListEnabled;
	}
//	public int getVlanId() {
//		return vlanId;
//	}
//	public void setVlanId(int vlanId) {
//		this.vlanId = vlanId;
//	}
	public int getMacCount() {
		return macCount;
	}
	public void setMacCount(int macCount) {
		this.macCount = macCount;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getAssociateVlan() {
		return associateVlan;
	}
	public void setAssociateVlan(int associateVlan) {
		this.associateVlan = associateVlan;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
}
