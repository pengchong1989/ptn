package com.nms.ui.ptn.performance.model;

import java.util.ArrayList;
import java.util.List;

public class CurrPerformCountFilter implements java.io.Serializable{
	private static final long serialVersionUID = -1909537421406330874L;

	//监控网元
	private List<Integer> siteInsts = new ArrayList<Integer>();
	//监控对象类型  端口/段/tunnel/pw/以太网业务
	private List<String> capabilityNameList = new ArrayList<String>();
	//监控对象
	private Object objectName;
	//采样周期
	private String monitorCycle;//20s/40s/60s/其他
	//性能类型 收字节数/发字节数
	private String performanceType;
	public List<Integer> getSiteInsts() {
		return siteInsts;
	}
	public void setSiteInsts(List<Integer> siteInsts) {
		this.siteInsts = siteInsts;
	}
	public List<String> getCapabilityNameList() {
		return capabilityNameList;
	}
	public void setCapabilityNameList(List<String> capabilityNameList) {
		this.capabilityNameList = capabilityNameList;
	}
	public Object getObjectName() {
		return objectName;
	}
	public void setObjectName(Object objectName) {
		this.objectName = objectName;
	}
	public String getMonitorCycle() {
		return monitorCycle;
	}
	public void setMonitorCycle(String monitorCycle) {
		this.monitorCycle = monitorCycle;
	}
	public String getPerformanceType() {
		return performanceType;
	}
	public void setPerformanceType(String performanceType) {
		this.performanceType = performanceType;
	}
}
