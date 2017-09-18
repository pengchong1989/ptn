package com.nms.db.bean.ptn.path.protect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.CommonBean;

/**
 * 环网保护倒换对象
 * @author guoqc
 *
 */
public class LoopProRotate implements Serializable{
	private static final long serialVersionUID = -1445861332803510335L;
	private int actionType;//命令类型  : 00/01/02/03/04/05/06=清除倒换/锁定工作信道/锁定当前状态/强制倒换/人工倒换/练习
	private String actionTypeLog;//log日志用到
	private int ringId;//ff/01/02/…/10H=所有环/环1/环2/…/环16
	private boolean allRotateLog;//是否倒换所有环,log日志用到
	private int direction;//方向: 01/02 = West/East
	private String directionLog;//方向，log日志用到
	private List<Integer> siteIdList = new ArrayList<Integer>();//环网对象中所有网元
	private List<CommonBean> siteNameListLog = null;//环网对象中所有网元,log日志用到
	
	public String getActionTypeLog() {
		return actionTypeLog;
	}
	public void setActionTypeLog(String actionTypeLog) {
		this.actionTypeLog = actionTypeLog;
	}
	public boolean isAllRotateLog() {
		return allRotateLog;
	}
	public void setAllRotateLog(boolean allRotateLog) {
		this.allRotateLog = allRotateLog;
	}
	public String getDirectionLog() {
		return directionLog;
	}
	public void setDirectionLog(String directionLog) {
		this.directionLog = directionLog;
	}
	public List<CommonBean> getSiteNameListLog() {
		return siteNameListLog;
	}
	public void setSiteNameListLog(List<CommonBean> siteNameListLog) {
		this.siteNameListLog = siteNameListLog;
	}
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
