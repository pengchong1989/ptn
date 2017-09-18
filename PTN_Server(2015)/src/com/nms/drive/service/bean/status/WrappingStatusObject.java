package com.nms.drive.service.bean.status;

public class WrappingStatusObject {
	private int wrappingId;//环id
	private int westPort;//西向端口
	private int eastPort;//东向端口
	private int protectType;//保护类型
	private int rorateStatus;//倒换状态
	private int totalNumber;//节点总数
	private int localNumber;//本站节点id
	private int westAlarm;//西向线路告警
	private int eastAlarm;//东向线路告警
	private int westReceiveAps;//西向收aps信息
	private int eastReceiveAps;//东向收aps信息
	private int westSendAps;//西向发aps信息
	private int eastSendAps;//东向发aps信息
	private int delayTime;//拖延时间
	private int backType;//返回类型
	public int getWrappingId() {
		return wrappingId;
	}
	public void setWrappingId(int wrappingId) {
		this.wrappingId = wrappingId;
	}
	public int getWestPort() {
		return westPort;
	}
	public void setWestPort(int westPort) {
		this.westPort = westPort;
	}
	public int getEastPort() {
		return eastPort;
	}
	public void setEastPort(int eastPort) {
		this.eastPort = eastPort;
	}
	public int getProtectType() {
		return protectType;
	}
	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}
	public int getRorateStatus() {
		return rorateStatus;
	}
	public void setRorateStatus(int rorateStatus) {
		this.rorateStatus = rorateStatus;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public int getLocalNumber() {
		return localNumber;
	}
	public void setLocalNumber(int localNumber) {
		this.localNumber = localNumber;
	}
	public int getWestAlarm() {
		return westAlarm;
	}
	public void setWestAlarm(int westAlarm) {
		this.westAlarm = westAlarm;
	}
	public int getEastAlarm() {
		return eastAlarm;
	}
	public void setEastAlarm(int eastAlarm) {
		this.eastAlarm = eastAlarm;
	}
	public int getWestReceiveAps() {
		return westReceiveAps;
	}
	public void setWestReceiveAps(int westReceiveAps) {
		this.westReceiveAps = westReceiveAps;
	}
	public int getEastReceiveAps() {
		return eastReceiveAps;
	}
	public void setEastReceiveAps(int eastReceiveAps) {
		this.eastReceiveAps = eastReceiveAps;
	}
	public int getWestSendAps() {
		return westSendAps;
	}
	public void setWestSendAps(int westSendAps) {
		this.westSendAps = westSendAps;
	}
	public int getEastSendAps() {
		return eastSendAps;
	}
	public void setEastSendAps(int eastSendAps) {
		this.eastSendAps = eastSendAps;
	}
	public int getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}
	public int getBackType() {
		return backType;
	}
	public void setBackType(int backType) {
		this.backType = backType;
	}
	
}
