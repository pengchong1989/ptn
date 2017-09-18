package com.nms.drivechenxiao.service.bean.tunnel;

public class TunnelObject {

	private Protection protection = new Protection();
	private LSPObject[] lSPObjects = new LSPObject[2];

	private String name = "";// 名称 **
	private String ifname = "";// 接口名
	private String ifindex = "";// 接口索引值
	private String desc = "";// 接口描述
	private String admin = "";// 接口管理状态
	private String oper = "";// 接口工作状态
	private String ref = "";//	
	private String role = "";// LSP角色(ingress(A)|egress(Z)) **
	private String peerid = "";// PEER TUNNEL ID **
	private String id = "";// TUNNEL ID
	private String owner = "";// TUNNEL创建者
	private String dualid = "";// 双归保护组ID
	private String passtunnel = "";// 是否是passtunnel
	private String lspw = "";// 工作LSP ID
	private String lspqostype = "";// TUNNEL的QoS 类型(elsp|eelsp|llsp)
	private String qos = "";// TUNNEL的QoS 类型(elsp|eelsp|llsp) **
	private String hwres = "";
	private String hwresref = "";
	private boolean deleteQos;
	private boolean isCreateQos=false;	//创建tunnel时，是否创建qos true创建  false不创建

	public boolean isDeleteQos() {
		return deleteQos;
	}

	public void setDeleteQos(boolean deleteQos) {
		this.deleteQos = deleteQos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Protection getProtection() {
		return protection;
	}

	public void setProtection(Protection protection) {
		this.protection = protection;
	}

	public String getIfname() {
		return ifname;
	}

	public void setIfname(String ifname) {
		this.ifname = ifname;
	}

	public String getIfindex() {
		return ifindex;
	}

	public void setIfindex(String ifindex) {
		this.ifindex = ifindex;
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

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPeerid() {
		return peerid;
	}

	public void setPeerid(String peerid) {
		this.peerid = peerid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDualid() {
		return dualid;
	}

	public void setDualid(String dualid) {
		this.dualid = dualid;
	}

	public String getPasstunnel() {
		return passtunnel;
	}

	public void setPasstunnel(String passtunnel) {
		this.passtunnel = passtunnel;
	}

	public String getLspw() {
		return lspw;
	}

	public void setLspw(String lspw) {
		this.lspw = lspw;
	}

	public String getLspqostype() {
		return lspqostype;
	}

	public void setLspqostype(String lspqostype) {
		this.lspqostype = lspqostype;
	}

	public String getQos() {
		return qos;
	}

	public void setQos(String qos) {
		this.qos = qos;
	}

	public String getHwres() {
		return hwres;
	}

	public void setHwres(String hwres) {
		this.hwres = hwres;
	}

	public String getHwresref() {
		return hwresref;
	}

	public void setHwresref(String hwresref) {
		this.hwresref = hwresref;
	}

	public LSPObject[] getLSPObjects() {
		return lSPObjects;
	}

	public void setLSPObjects(LSPObject[] objects) {
		lSPObjects = objects;
	}

	public boolean isCreateQos() {
		return isCreateQos;
	}

	public void setCreateQos(boolean isCreateQos) {
		this.isCreateQos = isCreateQos;
	}
	
}
