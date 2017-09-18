package com.nms.ui.ptn.performance.model;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;

public class PerformanceTaskFilter implements java.io.Serializable{
	
	public PerformanceTaskFilter() {
		siteInsts = new ArrayList<Integer>();
		slotInsts = new ArrayList<Integer>();
	}
	private EObjectType objectType;
	//监控网元
	private List<Integer> siteInsts;
	//监控板卡
	private List<Integer> slotInsts;
	//监控周期
	private EMonitorCycle monitorCycle;
	public List<Integer> getSiteInsts() {
		return siteInsts;
	}
	public void setSiteInsts(List<Integer> siteInsts) {
		this.siteInsts = siteInsts;
	}
	public List<Integer> getSlotInsts() {
		return slotInsts;
	}
	public void setSlotInsts(List<Integer> cardInsts) {
		this.slotInsts = cardInsts;
	}
	public EMonitorCycle getMonitorCycle() {
		return monitorCycle;
	}
	public void setMonitorCycle(EMonitorCycle monitorCycle) {
		this.monitorCycle = monitorCycle;
	}
	public EObjectType getObjectType() {
		return objectType;
	}
	public void setObjectType(EObjectType objectType) {
		this.objectType = objectType;
	}
}
