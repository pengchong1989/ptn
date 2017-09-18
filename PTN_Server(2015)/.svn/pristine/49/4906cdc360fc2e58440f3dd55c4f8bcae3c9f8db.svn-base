package com.nms.ui.ptn.performance.model;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;

public class HisPerformanceFilter {
	public HisPerformanceFilter() {
		siteInsts = new ArrayList<Integer>();
		slotInsts = new ArrayList<Integer>();
		capabilityIdList = new ArrayList<Integer>();
		CapabilityNameList=new ArrayList<String>();
	}
	private EObjectType objectType;
	//监控网元
	private List<Integer> siteInsts;
	//监控板卡
	private List<Integer> slotInsts;
	//性能类型表capability  数据库id集合
	private List<Integer> capabilityIdList;
	private String typeStr;
	//监控周期
	private EMonitorCycle monitorCycle;
	//是否过滤零值
	private int fiterZero;
	//时间的过滤
	private long startTime;
	private long endTime;
	
	private int isCardOrSite = -1;
	
	private List<String> CapabilityNameList;////性能类型表capability  数据库描述
	
	private String objectName;//添加历史性能过滤条件 ： 端口名称标识  --如  "ge.1.2","ge.1.3"
	
	private List<Integer> performanceCodeList = new ArrayList<Integer>();
	
	public List<Integer> getPerformanceCodeList() {
		return performanceCodeList;
	}
	public void setPerformanceCodeList(List<Integer> performanceCodeList) {
		this.performanceCodeList = performanceCodeList;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public int getIsCardOrSite() {
		return isCardOrSite;
	}
	public void setIsCardOrSite(int isCardOrSite) {
		this.isCardOrSite = isCardOrSite;
	}
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
	public List<Integer> getCapabilityIdList() {
		return capabilityIdList;
	}
	public void setCapabilityIdList(List<Integer> capabilityIdList) {
		this.capabilityIdList = capabilityIdList;
	}
	public EMonitorCycle getMonitorCycle() {
		return monitorCycle;
	}
	public void setMonitorCycle(EMonitorCycle monitorCycle) {
		this.monitorCycle = monitorCycle;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public EObjectType getObjectType() {
		return objectType;
	}
	public void setObjectType(EObjectType objectType) {
		this.objectType = objectType;
	}
	public int getFiterZero() {
		return fiterZero;
	}
	public void setFiterZero(int fiterZero) {
		this.fiterZero = fiterZero;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public List<String> getCapabilityNameList() {
		return CapabilityNameList;
	}
	public void setCapabilityNameList(List<String> capabilityNameList) {
		CapabilityNameList = capabilityNameList;
	}
	
	
}
