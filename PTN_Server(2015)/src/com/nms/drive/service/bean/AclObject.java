package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

public class AclObject {
	
	private int reserve = 0 ;//备用
	private List<AclInfoObject> aclInfoObjectList = new ArrayList<AclInfoObject>();
	
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public List<AclInfoObject> getAclInfoObjectList() {
		return aclInfoObjectList;
	}
	public void setAclInfoObjectList(List<AclInfoObject> aclInfoObjectList) {
		this.aclInfoObjectList = aclInfoObjectList;
	}
	
}
