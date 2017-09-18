package com.nms.drive.service.bean;

public class ResponsePan {
	private String NEAddr = "";//NE地址1,NE地址2
	private String panType = "";//盘类型1,盘类型2,盘类型3,盘类型4
	private String panAddr = "";//盘地址1,盘地址2
	public String getNEAddr() {
		return NEAddr;
	}
	public void setNEAddr(String addr) {
		NEAddr = addr;
	}
	public String getPanType() {
		return panType;
	}
	public void setPanType(String panType) {
		this.panType = panType;
	}
	public String getPanAddr() {
		return panAddr;
	}
	public void setPanAddr(String panAddr) {
		this.panAddr = panAddr;
	}
}
