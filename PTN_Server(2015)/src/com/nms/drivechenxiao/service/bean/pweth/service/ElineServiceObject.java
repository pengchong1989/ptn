package com.nms.drivechenxiao.service.bean.pweth.service;

public class ElineServiceObject {

	private String type = "";//**
	private String vpn = "";//**
	private String hwres = "";
	
	public String toString(){
		StringBuffer sb=new StringBuffer().append(" ;type=").append(type)
		.append(" ;vpn=").append(vpn).append(" ;hwres=").append(hwres);
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

}
