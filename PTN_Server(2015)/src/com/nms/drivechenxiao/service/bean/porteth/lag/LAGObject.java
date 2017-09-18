package com.nms.drivechenxiao.service.bean.porteth.lag;

public class LAGObject {

	private String name = "";// 名称
	private String lagid = ""; // 链路聚合组ID
	private String status = ""; // 聚合状态
	private String wtrtime = ""; // LACP Wtr值，单位为s,在静态端口聚合up起来有用，为0表示不Wtr

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLagid() {
		return lagid;
	}

	public void setLagid(String lagid) {
		this.lagid = lagid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWtrtime() {
		return wtrtime;
	}

	public void setWtrtime(String wtrtime) {
		this.wtrtime = wtrtime;
	}

}
