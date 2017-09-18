package com.nms.ui.ptn.performance.model;

public class PerformanceRAMInfo {

	private int id;
	private String timeSelect;//是否选中了时间
	private String timeValue;//时间天数
	private String ramPerformanceSelect;//是否选中内存设置
	private String ramValue;
	private String userName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTimeSelect() {
		return timeSelect;
	}
	public void setTimeSelect(String timeSelect) {
		this.timeSelect = timeSelect;
	}
	public String getTimeValue() {
		return timeValue;
	}
	public void setTimeValue(String timeValue) {
		this.timeValue = timeValue;
	}
	public String getRamPerformanceSelect() {
		return ramPerformanceSelect;
	}
	public void setRamPerformanceSelect(String ramPerformanceSelect) {
		this.ramPerformanceSelect = ramPerformanceSelect;
	}
	public String getRamValue() {
		return ramValue;
	}
	public void setRamValue(String ramValue) {
		this.ramValue = ramValue;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
