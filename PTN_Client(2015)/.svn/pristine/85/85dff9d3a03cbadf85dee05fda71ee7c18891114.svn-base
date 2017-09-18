package com.nms.db.bean.ptn.path.protect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 环网保护倒换对象
 * @author guoqc
 *
 */
public class LoopProRotate implements Serializable{
	private static final long serialVersionUID = -1445861332803510335L;
	private int actionType;//命令类型  : 00/01/02/03/04/05/06=清除倒换/锁定工作信道/锁定当前状态/强制倒换/人工倒换/练习
	private int ringId;//ff/01/02/…/10H=所有环/环1/环2/…/环16
	private int direction;//方向: 01/02 = West/East
	private List<Integer> siteIdList = new ArrayList<Integer>();//环网对象中所有网元
	public int getActionType() {
		return actionType;
	}
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	public int getRingId() {
		return ringId;
	}
	public void setRingId(int ringId) {
		this.ringId = ringId;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public List<Integer> getSiteIdList() {
		return siteIdList;
	}
	public void setSiteIdList(List<Integer> siteIdList) {
		this.siteIdList = siteIdList;
	}
}
