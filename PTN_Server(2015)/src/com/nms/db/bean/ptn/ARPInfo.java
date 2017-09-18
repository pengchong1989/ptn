package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;

public class ARPInfo extends ViewDataObj{

	private static final long serialVersionUID = -2514268440919026855L;
	private int id;//主键id
	private int pwProtectId;//PW保护ID
	private String dualName;//双规保护名称
	private String name;//本地名称
	private String sourceMac;//源MAC地址:0x00 00 00 00 00 01 
	private int vlanEnabled;//VLAN ：0/1=无/有
	private int vlanId;//VLAN值 范围2-4095
	private int vlanPri;//VLAN PRI:0-7
	private String sourceIp;//源IP地址 10.18.2.1
	private String targetIp;//目的IP地址 10.18.3.2
	private int enabled;//arp报文是否使能  0/1=不使能/使能
	private int siteId;
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPwProtectId() {
		return pwProtectId;
	}
	public void setPwProtectId(int pwProtectId) {
		this.pwProtectId = pwProtectId;
	}
	public String getDualName() {
		return dualName;
	}
	public void setDualName(String dualName) {
		this.dualName = dualName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSourceMac() {
		return sourceMac;
	}
	public void setSourceMac(String sourceMac) {
		this.sourceMac = sourceMac;
	}
	public int getVlanEnabled() {
		return vlanEnabled;
	}
	public void setVlanEnabled(int vlanEnabled) {
		this.vlanEnabled = vlanEnabled;
	}
	public int getVlanId() {
		return vlanId;
	}
	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}
	public int getVlanPri() {
		return vlanPri;
	}
	public void setVlanPri(int vlanPri) {
		this.vlanPri = vlanPri;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	@Override
	public void putObjectProperty() {
		
	}
	
}
