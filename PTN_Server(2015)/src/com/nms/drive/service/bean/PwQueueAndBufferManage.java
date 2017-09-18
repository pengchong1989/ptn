package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *PW层队列调度及缓存管理策略对象
 * @author hulei
 *
 */
public class PwQueueAndBufferManage {

	private List<PwQueueManage> pwQueueManageList = new ArrayList<PwQueueManage>();// (出口队列调度策略)(队列)模式
	private List<PwBufferManage> pwBufferManageList = new ArrayList<PwBufferManage>();// (队列)权重
	private int exitSpeedLimit = 0;// PW层出口限速
	private int reserve = 0;// 备用,00

	public List<PwQueueManage> getPwQueueManageList() {
		return pwQueueManageList;
	}

	public void setPwQueueManageList(List<PwQueueManage> pwQueueManageList) {
		this.pwQueueManageList = pwQueueManageList;
	}

	public List<PwBufferManage> getPwBufferManageList() {
		return pwBufferManageList;
	}

	public void setPwBufferManageList(List<PwBufferManage> pwBufferManageList) {
		this.pwBufferManageList = pwBufferManageList;
	}

	public int getExitSpeedLimit() {
		return exitSpeedLimit;
	}

	public void setExitSpeedLimit(int exitSpeedLimit) {
		this.exitSpeedLimit = exitSpeedLimit;
	}

	public int getReserve() {
		return reserve;
	}

	public void setReserve(int reserve) {
		this.reserve = reserve;
	}

}
