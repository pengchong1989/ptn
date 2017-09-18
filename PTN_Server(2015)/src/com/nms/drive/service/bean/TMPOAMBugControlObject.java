package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * TMPOAMBugControlObject ：TMP OAM故障管理配置
 * @author 彭冲
 *
 */
public class TMPOAMBugControlObject {
	private int reserve = 0 ;//备用
	private List<TMPOAMBugControlInfoObject> tmpOamBugControlObjectlist = new ArrayList<TMPOAMBugControlInfoObject>();
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public List<TMPOAMBugControlInfoObject> getTmpOamBugControlObjectlist() {
		return tmpOamBugControlObjectlist;
	}
	public void setTmpOamBugControlObjectlist(List<TMPOAMBugControlInfoObject> tmpOamBugControlObjectlist) {
		this.tmpOamBugControlObjectlist = tmpOamBugControlObjectlist;
	}
}
