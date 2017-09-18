package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * ETHOAM 以太网OAM
 * @author 彭冲
 *
 */
public class ETHOAM {

	private int timeOut = 0;// 环回帧超时时间
	private int mel = 0;// MEL

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public int getMel() {
		return mel;
	}

	public void setMel(int mel) {
		this.mel = mel;
	}

	private List<ETHOAMInfo> eTHOAMInfoList = new ArrayList<ETHOAMInfo>();

	public List<ETHOAMInfo> getETHOAMInfoList() {
		return eTHOAMInfoList;
	}

	public void setETHOAMInfoList(List<ETHOAMInfo> infoList) {
		eTHOAMInfoList = infoList;
	}

}
