package com.nms.drivechenxiao.service.bean.service;

import java.util.ArrayList;
import java.util.List;

public class TreePortList {
	private List<String> pwList = new ArrayList<String>();
	private String ac = "";
	private boolean status = false;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<String> getPwList() {
		return pwList;
	}

	public void setPwList(List<String> pwList) {
		this.pwList = pwList;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

}
