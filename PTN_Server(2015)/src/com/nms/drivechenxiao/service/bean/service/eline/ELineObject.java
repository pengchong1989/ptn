package com.nms.drivechenxiao.service.bean.service.eline;

import com.nms.drivechenxiao.service.bean.service.PortList;

public class ELineObject {

	private String vpnid = "";// VPN ID         
	private String name = "";// VPN名称 **
	private String desc = "";// VPN描述           1-16(txt)
	private String admin = "";// VPN管理状态  up(使能)/down          (***注意：！！只可以更改admin)
	private String oper = "";// VPN工作状态  (tsf/admindown 随admin-up更改)   / tsf
	
	private String dualid = "";// 双归保护组ID
	private String svctype = "";// 0=normal eline,1=eline-elan,2=eline-etree
	private String pwnum = "";// pwup number
	private String acnum = "";// acup number
	private PortList portList = new PortList();

	public PortList getPortList() {
		return portList;
	}

	public void setPortList(PortList portList) {
		this.portList = portList;
	}

	public String getVpnid() {
		return vpnid;
	}

	public void setVpnid(String vpnid) {
		this.vpnid = vpnid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getDualid() {
		return dualid;
	}

	public void setDualid(String dualid) {
		this.dualid = dualid;
	}

	public String getSvctype() {
		return svctype;
	}

	public void setSvctype(String svctype) {
		this.svctype = svctype;
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

}
