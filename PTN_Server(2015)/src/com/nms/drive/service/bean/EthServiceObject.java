package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

public class EthServiceObject {
	
	private int reserve = 0 ;//备用
	private List<EthServiceInfoObject> ethServiceInfoObjectList = new ArrayList<EthServiceInfoObject>();
	
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public List<EthServiceInfoObject> getEthServiceInfoObjectList() {
		return ethServiceInfoObjectList;
	}
	public void setEthServiceInfoObjectList(List<EthServiceInfoObject> ethServiceInfoObjectList) {
		this.ethServiceInfoObjectList = ethServiceInfoObjectList;
	}
	
}
