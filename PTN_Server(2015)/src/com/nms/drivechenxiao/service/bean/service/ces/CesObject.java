package com.nms.drivechenxiao.service.bean.service.ces;

import com.nms.drivechenxiao.service.bean.service.PortList;

public class CesObject {
	private String vpnid = "";//VPN ID     
	private String name = "";//VPN名称    
	private String desc = "";//VPN描述    
	private String admin = "";//VPN管理状态
	private String oper = "";//VPN工作状态
	private String pwnum = "";//pwup number
	private String acnum = "";//acup number
	private PortList portList = new PortList();

	public String getVpnid() {
		return vpnid;
	}

	public void setVpnid(String vpnid) {
		this.vpnid = vpnid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getPwnum() {
		return pwnum;
	}

	public void setPwnum(String pwnum) {
		this.pwnum = pwnum;
	}

	public String getAcnum() {
		return acnum;
	}

	public void setAcnum(String acnum) {
		this.acnum = acnum;
	}

	public PortList getPortList() {
		return portList;
	}

	public void setPortList(PortList portList) {
		this.portList = portList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
