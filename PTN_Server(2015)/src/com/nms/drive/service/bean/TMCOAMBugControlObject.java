package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * TMCOAMBugControlObject： TMC OAM故障管理配置
 * @author 彭冲
 *
 */
public class TMCOAMBugControlObject {
	private int reserve = 0 ;//备用
	private List<TMCOAMBugControlInfoObject> tmcOamBugControlObjectlist = new ArrayList<TMCOAMBugControlInfoObject>();
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public List<TMCOAMBugControlInfoObject> getTmcOamBugControlObjectlist() {
		return tmcOamBugControlObjectlist;
	}
	public void setTmcOamBugControlObjectlist(List<TMCOAMBugControlInfoObject> tmpOamBugControlObjectlist) {
		this.tmcOamBugControlObjectlist = tmpOamBugControlObjectlist;
	}
}
