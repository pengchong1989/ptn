package com.nms.ui.ptn.performance.model;

import com.nms.ui.frame.ViewDataObj;

public class CurrentPerformanceInfo extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CurrentPerformanceInfo() {
	}
	private String perforObj;
	private String perforType;
	private int perforCode;
	private int perforValue;
	private String perforDesc;
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		this.getClientProperties().put("perObj", this.getPerforObj());
		this.getClientProperties().put("perType", this.getPerforType());
		this.getClientProperties().put("perCode", this.getPerforCode());
		this.getClientProperties().put("perValue", this.getPerforValue());
		this.getClientProperties().put("perDesc", this.getPerforDesc());
	}

	public String getPerforObj() {
		return perforObj;
	}
	public void setPerforObj(String perforObj) {
		this.perforObj = perforObj;
	}
	public String getPerforType() {
		return perforType;
	}
	public void setPerforType(String perforType) {
		this.perforType = perforType;
	}
	public int getPerforCode() {
		return perforCode;
	}
	public void setPerforCode(int perforCode) {
		this.perforCode = perforCode;
	}
	public int getPerforValue() {
		return perforValue;
	}
	public void setPerforValue(int perforValue) {
		this.perforValue = perforValue;
	}
	public String getPerforDesc() {
		return perforDesc;
	}
	public void setPerforDesc(String perforDesc) {
		this.perforDesc = perforDesc;
	}
}
