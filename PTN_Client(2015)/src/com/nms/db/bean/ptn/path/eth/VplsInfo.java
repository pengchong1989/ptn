package com.nms.db.bean.ptn.path.eth;

import java.util.List;

/**
 * 用户操作日志显示时需要用到此类
 * 用于显示vpls业务etree和elan以及dual数据模型
 * @author guoqc
 */
public class VplsInfo {
	private String vplsName;
	private String clientName;
	private int activedStatus;
	private List<EtreeInfo> etreeInfoList = null;
	private List<ElanInfo> elanInfoList = null;
	private DualInfo dualInfo = null;
	
	public DualInfo getDualInfo() {
		return dualInfo;
	}
	public void setDualInfo(DualInfo dualInfo) {
		this.dualInfo = dualInfo;
	}
	public String getVplsName() {
		return vplsName;
	}
	public void setVplsName(String vplsName) {
		this.vplsName = vplsName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getActiveStatus() {
		return activedStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activedStatus = activeStatus;
	}
	public List<EtreeInfo> getEtreeInfoList() {
		return etreeInfoList;
	}
	public void setEtreeInfoList(List<EtreeInfo> etreeInfoList) {
		this.etreeInfoList = etreeInfoList;
	}
	public List<ElanInfo> getElanInfoList() {
		return elanInfoList;
	}
	public void setElanInfoList(List<ElanInfo> elanInfoList) {
		this.elanInfoList = elanInfoList;
	}
	
}
