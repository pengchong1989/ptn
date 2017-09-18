package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * TMSOAMBugControlObject ：TMS OAM故障管理配置
 * @author 彭冲
 *
 */
public class TMSOAMBugControlObject {
	private int reserve = 0 ;//备用
	private List<TMSOAMBugControlInfoObject> tmsOamBugControlObjectlist = new ArrayList<TMSOAMBugControlInfoObject>();
	
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public List<TMSOAMBugControlInfoObject> getTmsOamBugControlObjectlist() {
		return tmsOamBugControlObjectlist;
	}
	public void setTmsOamBugControlObjectlist(List<TMSOAMBugControlInfoObject> tmpOamBugControlObjectlist) {
		this.tmsOamBugControlObjectlist = tmpOamBugControlObjectlist;
	}
}
