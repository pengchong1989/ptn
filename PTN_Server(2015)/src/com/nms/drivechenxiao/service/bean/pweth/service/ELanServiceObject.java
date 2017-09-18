package com.nms.drivechenxiao.service.bean.pweth.service;

public class ELanServiceObject {

	private String type = "";//**
	private String vpn = "";//** 与ELanObjectVPN名称相同
	private String hwres = "";
	private String learn = "";// 地址学习(true|false)
	private String spw = "";// spoken vc

	public String toString(){
		StringBuffer sb=new StringBuffer().append(" type=").append(type)
		.append(" ;vpn=").append(vpn).append(" ;hwres=").append(hwres)
		.append(" ;learn=").append(learn).append(" ;spw=").append(spw);
		return sb.toString();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVpn() {
		return vpn;
	}

	public void setVpn(String vpn) {
		this.vpn = vpn;
	}

	public String getHwres() {
		return hwres;
	}

	public void setHwres(String hwres) {
		this.hwres = hwres;
	}

	public String getLearn() {
		return learn;
	}

	public void setLearn(String learn) {
		this.learn = learn;
	}

	public String getSpw() {
		return spw;
	}

	public void setSpw(String spw) {
		this.spw = spw;
	}

}
