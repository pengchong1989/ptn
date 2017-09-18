package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

public class ETHOAMAllObject {
	private int reserve = 0 ;//备用
	private List<ETHOAMObject> ethoamObjectList = new ArrayList<ETHOAMObject>();
	
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public List<ETHOAMObject> getEthoamObjectList() {
		return ethoamObjectList;
	}
	public void setEthoamObjectList(List<ETHOAMObject> ethoamObjectList) {
		this.ethoamObjectList = ethoamObjectList;
	}
	
}
