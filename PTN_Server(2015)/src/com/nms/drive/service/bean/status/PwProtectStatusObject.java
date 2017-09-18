package com.nms.drive.service.bean.status;

public class PwProtectStatusObject {

	private int protectType;//保护类型
	private String mainPort;//主用端口
	private String mainLspName;//主用Lsp名称
	private String mainPwName;//主用Pw名称
	private int mainPwAlarm;//主用pw告警
	private String standPort;//备用端口
	private String standLspName;//备用Lsp名称
	private String standPwName;//备用Pw名称
	private int standPwAlarm;//备用pw告警
	private int rorateStatus;//倒换状态
	private int delaytime;//拖延时间
	private int receiveAps;//接收APS
	private int launchAps;//发送APS
	private int backType;//返回类型
	public int getProtectType() {
		return protectType;
	}
	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}
	public String getMainPort() {
		return mainPort;
	}
	public void setMainPort(String mainPort) {
		this.mainPort = mainPort;
	}
	public String getMainLspName() {
		return mainLspName;
	}
	public void setMainLspName(String mainLspName) {
		this.mainLspName = mainLspName;
	}
	public String getMainPwName() {
		return mainPwName;
	}
	public void setMainPwName(String mainPwName) {
		this.mainPwName = mainPwName;
	}
	public int getMainPwAlarm() {
		return mainPwAlarm;
	}
	public void setMainPwAlarm(int mainPwAlarm) {
		this.mainPwAlarm = mainPwAlarm;
	}
	public String getStandPort() {
		return standPort;
	}
	public void setStandPort(String standPort) {
		this.standPort = standPort;
	}
	public String getStandLspName() {
		return standLspName;
	}
	public void setStandLspName(String standLspName) {
		this.standLspName = standLspName;
	}
	public String getStandPwName() {
		return standPwName;
	}
	public void setStandPwName(String standPwName) {
		this.standPwName = standPwName;
	}
	public int getStandPwAlarm() {
		return standPwAlarm;
	}
	public void setStandPwAlarm(int standPwAlarm) {
		this.standPwAlarm = standPwAlarm;
	}
	public int getRorateStatus() {
		return rorateStatus;
	}
	public void setRorateStatus(int rorateStatus) {
		this.rorateStatus = rorateStatus;
	}
	public int getDelaytime() {
		return delaytime;
	}
	public void setDelaytime(int delaytime) {
		this.delaytime = delaytime;
	}
	public int getReceiveAps() {
		return receiveAps;
	}
	public void setReceiveAps(int receiveAps) {
		this.receiveAps = receiveAps;
	}
	public int getLaunchAps() {
		return launchAps;
	}
	public void setLaunchAps(int launchAps) {
		this.launchAps = launchAps;
	}
	public int getBackType() {
		return backType;
	}
	public void setBackType(int backType) {
		this.backType = backType;
	}
	
}
