package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

public class ETHOAMBugControlObject {
	private int reserve = 0 ;//备用
	private List<ETHOAMObject> tmcOamBugControlObjectlist = new ArrayList<ETHOAMObject>();
	
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public List<ETHOAMObject> getTmcOamBugControlObjectlist() {
		return tmcOamBugControlObjectlist;
	}
	public void setTmcOamBugControlObjectlist(
			List<ETHOAMObject> tmcOamBugControlObjectlist) {
		this.tmcOamBugControlObjectlist = tmcOamBugControlObjectlist;
	}
	
}
