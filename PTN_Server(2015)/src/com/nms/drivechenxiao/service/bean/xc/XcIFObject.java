package com.nms.drivechenxiao.service.bean.xc;

public class XcIFObject {
	
	private String name=""; //**
	private String admin = "";// MPLSXC管理状态
	private String oper = "";// 接口工作状态
	private String carrierif = "";// xcif的承载接口 **
	private String ifindex = "";
	private String ifname = "";
	private String inlabel = "";// xcif输入标签 **
	private String outlabel = "";// xcif输出标签 **
	private String perprofile = "";// 性能profile名字
	private String lblhwres = "";
	private String qoshwres = "";

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

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getCarrierif() {
		return carrierif;
	}

	public void setCarrierif(String carrierif) {
		this.carrierif = carrierif;
	}

	public String getIfindex() {
		return ifindex;
	}

	public void setIfindex(String ifindex) {
		this.ifindex = ifindex;
	}

	public String getIfname() {
		return ifname;
	}

	public void setIfname(String ifname) {
		this.ifname = ifname;
	}

	public String getInlabel() {
		return inlabel;
	}

	public void setInlabel(String inlabel) {
		this.inlabel = inlabel;
	}

	public String getOutlabel() {
		return outlabel;
	}

	public void setOutlabel(String outlabel) {
		this.outlabel = outlabel;
	}

	public String getPerprofile() {
		return perprofile;
	}

	public void setPerprofile(String perprofile) {
		this.perprofile = perprofile;
	}

	public String getLblhwres() {
		return lblhwres;
	}

	public void setLblhwres(String lblhwres) {
		this.lblhwres = lblhwres;
	}

	public String getQoshwres() {
		return qoshwres;
	}

	public void setQoshwres(String qoshwres) {
		this.qoshwres = qoshwres;
	}

}
