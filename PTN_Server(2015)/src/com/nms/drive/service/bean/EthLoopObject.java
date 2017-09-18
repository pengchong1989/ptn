package com.nms.drive.service.bean;

/**
 * @author zk
 *function:以太网环回 驱动对象
 */
public class EthLoopObject {
	
	private int loopEnableObject = 0;//环回使能：0/1 = 不使能/使能
	private int portNumberObject = 0;//端口号：0/1/2/…/6 =无/WAN1/WAN2/LAN1/LAN2/LAN3/LAN4
	private int loopRuleObject = 0; //环回规则：0/1/2/3/4=端口环回/vlan环回/源MAC环回/目的MAC环回/源IP环回/目的IP环回
	private String vlan = "1"; //Vlan（1-4094）
	private String macAddress = "00-00-00-00-00-00";//MAC
	private String ipAddress = "0.0.0.0" ;//IP
	public int getLoopEnableObject() {
		return loopEnableObject;
	}
	public void setLoopEnableObject(int loopEnableObject) {
		this.loopEnableObject = loopEnableObject;
	}
	public int getPortNumberObject() {
		return portNumberObject;
	}
	public void setPortNumberObject(int portNumberObject) {
		this.portNumberObject = portNumberObject;
	}
	public int getLoopRuleObject() {
		return loopRuleObject;
	}
	public void setLoopRuleObject(int loopRuleObject) {
		this.loopRuleObject = loopRuleObject;
	}
	public String getVlan() {
		return vlan;
	}
	public void setVlan(String vlan) {
		this.vlan = vlan;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
