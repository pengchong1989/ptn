package com.nms.drivechenxiao.service.bean.service.etree;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.service.TreePortList;

public class ETreeObject {

	private TreePortList portList = new TreePortList();
	private String vpnid = "";// VPN ID
	private String name = "";// VPN名称  **
	private String desc = "";// VPN描述
	private String admin = "";// VPN管理状态
	private String oper = "";// VPN工作状态
	private String dualid = "";// 双归保护组ID
	private String limitaddr = "";// ETREE/ELAN MAC地址转发表项个数
	private String learnrule = "";// 学习规则
	private String bcastctrl = "";// 广播报文抑制                   bool
	private String mcastctrl = "";// 组播报文抑制              bool
	private String dlfctrl = "";// 未知单播报文抑制         bool
	private String memcnt = "";
	private String root = "";// ETREE 根端口接口名
	private String svctype = "";// 0=normal etree,2=eline-etree
	private String acnum = "";// acup number
	private String pwnum = "";// pwup number
	/*
	 * 更新 etree 时，要删除的pw
	 */
	private List<PwEthObject> pwDelete=new ArrayList<PwEthObject>();
	/*
	 * 更新 etree 时，新增的pw
	 */
	private List<PwEthObject> pwInsert=new ArrayList<PwEthObject>();
	
	/**
	 * 修改Etree时
	 *    前台传人参数
	 *   true 修改前    ac作为 跟节点
	 *   false 修改前，pw作为跟节点或者根节点为空时
	 */
	private boolean flag;
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
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

	public TreePortList getPortList() {
		return portList;
	}

	public void setPortList(TreePortList portList) {
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

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getSvctype() {
		return svctype;
	}

	public void setSvctype(String svctype) {
		this.svctype = svctype;
	}

	public String getAcnum() {
		return acnum;
	}

	public void setAcnum(String acnum) {
		this.acnum = acnum;
	}

	public String getPwnum() {
		return pwnum;
	}

	public void setPwnum(String pwnum) {
		this.pwnum = pwnum;
	}

}
