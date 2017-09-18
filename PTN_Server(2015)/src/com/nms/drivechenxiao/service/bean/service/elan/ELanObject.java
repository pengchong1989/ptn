package com.nms.drivechenxiao.service.bean.service.elan;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.service.LanPortList;

public class ELanObject {
	private LanPortList portList = new LanPortList();
	private String vpnid = "";// VPN ID 
	private String name = "";// VPN名称 **
	private String desc = "";// VPN描述
	private String admin = "";// VPN管理状态  0,1,2
	private String oper = "";// VPN工作状态
	private String dualid = "";// 双归保护组ID
	private String limitaddr = "";// ETREE/ELAN MAC地址转发表项个数   [-1,32000]
	private String learnrule = "";// 学习规则
	private String bcastctrl = "";// 广播报文抑制
	private String mcastctrl = "";// 组播报文抑制
	private String dlfctrl = "";// 未知单播报文抑制
	private String memcnt = "";
	private String svctype = "";// 0=normal elan,1=eline-elan   双中心模式  0、1
	private String pwnum = "";// pwup number
	private String acnum = "";// acup number
	private String stpportnum = "";
	/*
	 * 更新 etree 时，要删除的pw
	 */
	private List<PwEthObject> pwDelete=new ArrayList<PwEthObject>();
	/*
	 * 更新 etree 时，新增的pw
	 */
	private List<PwEthObject> pwInsert=new ArrayList<PwEthObject>();
	
	
	public List<PwEthObject> getPwDelete() {
		return pwDelete;
	}

	public void setPwDelete(List<PwEthObject> pwDelete) {
		this.pwDelete = pwDelete;
	}

	public List<PwEthObject> getPwInsert() {
		return pwInsert;
	}

	public void setPwInsert(List<PwEthObject> pwInsert) {
		this.pwInsert = pwInsert;
	}

	public LanPortList getPortList() {
		return portList;
	}

	public void setPortList(LanPortList portList) {
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

	public String getLimitaddr() {
		return limitaddr;
	}

	public void setLimitaddr(String limitaddr) {
		this.limitaddr = limitaddr;
	}

	public String getLearnrule() {
		return learnrule;
	}

	public void setLearnrule(String learnrule) {
		this.learnrule = learnrule;
	}

	public String getBcastctrl() {
		return bcastctrl;
	}

	public void setBcastctrl(String bcastctrl) {
		this.bcastctrl = bcastctrl;
	}

	public String getMcastctrl() {
		return mcastctrl;
	}

	public void setMcastctrl(String mcastctrl) {
		this.mcastctrl = mcastctrl;
	}

	public String getDlfctrl() {
		return dlfctrl;
	}

	public void setDlfctrl(String dlfctrl) {
		this.dlfctrl = dlfctrl;
	}

	public String getMemcnt() {
		return memcnt;
	}

	public void setMemcnt(String memcnt) {
		this.memcnt = memcnt;
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

	public String getStpportnum() {
		return stpportnum;
	}

	public void setStpportnum(String stpportnum) {
		this.stpportnum = stpportnum;
	}

}
