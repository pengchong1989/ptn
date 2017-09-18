package com.nms.drive.service.bean;

public class QinQObject {
	private int QinQId;//1~256
	private int basedInVlanId;//类型基于内层VLAN标签：否/是=0/1
	private String tpId;//TPID：0x8100/0x9100/0x88a8
	private int aPortId;//端口号1:0/1/2/…/19H=无/LAN1/LAN2/LAN3/…/ LAN22 /WAN1/…/WAN4
	private int aPortVlanIdRule;//port1VLAN标签处理：增加（port1如果为uni则为增加，否则为透传）/透传=0/1/2
	private int minVlanId;//（port1如果为uni口）Port1匹配vlan 标签下限：1~4094 
	private int maxVlanId;//（port1如果为uni口）Port1匹配vlan标签上限：1~4094（大于或者等于下限）
	private int zPortId;//端口号2:0/1/2/…/19H=无/LAN1/LAN2/LAN3/…/ LAN22 /WAN1/…/WAN4
	private int zPortVlanIdRule;//port2VLAN标签处理：增加（port2如果为uni则为增加，否则为透传）/透传=0/1/2
	private int vlanId;//VLAN ID：1~4094 
	public int getQinQId() {
		return QinQId;
	}
	public void setQinQId(int qinQId) {
		QinQId = qinQId;
	}
	public int getBasedInVlanId() {
		return basedInVlanId;
	}
	public void setBasedInVlanId(int basedInVlanId) {
		this.basedInVlanId = basedInVlanId;
	}
	public String getTpId() {
		return tpId;
	}
	public void setTpId(String tpId) {
		this.tpId = tpId;
	}
	public int getMinVlanId() {
		return minVlanId;
	}
	public void setMinVlanId(int minVlanId) {
		this.minVlanId = minVlanId;
	}
	public int getMaxVlanId() {
		return maxVlanId;
	}
	public void setMaxVlanId(int maxVlanId) {
		this.maxVlanId = maxVlanId;
	}
	public int getVlanId() {
		return vlanId;
	}
	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}
	public int getaPortId() {
		return aPortId;
	}
	public void setaPortId(int aPortId) {
		this.aPortId = aPortId;
	}
	public int getaPortVlanIdRule() {
		return aPortVlanIdRule;
	}
	public void setaPortVlanIdRule(int aPortVlanIdRule) {
		this.aPortVlanIdRule = aPortVlanIdRule;
	}
	public int getzPortId() {
		return zPortId;
	}
	public void setzPortId(int zPortId) {
		this.zPortId = zPortId;
	}
	public int getzPortVlanIdRule() {
		return zPortVlanIdRule;
	}
	public void setzPortVlanIdRule(int zPortVlanIdRule) {
		this.zPortVlanIdRule = zPortVlanIdRule;
	}
	
}
