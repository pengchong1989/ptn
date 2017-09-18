package com.nms.db.bean.ptn.oamStatus;

import java.util.List;

import com.nms.db.bean.ptn.MacStudyBean;

public class OamStatusInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4383491262382185055L;
	private List<OamPingFrame> oamPingFrames;
	private List<OamTraceHops> oamTraceHops;
	private OamLinkStatusInfo OamLinkStatusInfo;//接入链路以太网oam状态查询bean属性
	private InsertLinkOamInfo insertLinkOamInfo;
	private OamMepInst oamMepInst ;//接入链路以太网oam对端MEP信息 bean属性 
	private MacStudyBean macStudyBean;
	
	public InsertLinkOamInfo getInsertLinkOamInfo() {
		return insertLinkOamInfo;
	}
	public void setInsertLinkOamInfo(InsertLinkOamInfo insertLinkOamInfo) {
		this.insertLinkOamInfo = insertLinkOamInfo;
	}
	public List<OamPingFrame> getOamPingFrames() {
		return oamPingFrames;
	}
	public void setOamPingFrames(List<OamPingFrame> oamPingFrames) {
		this.oamPingFrames = oamPingFrames;
	}
	public List<OamTraceHops> getOamTraceHops() {
		return oamTraceHops;
	}
	public void setOamTraceHops(List<OamTraceHops> oamTraceHops) {
		this.oamTraceHops = oamTraceHops;
	}
	public OamLinkStatusInfo getOamLinkStatusInfo() {
		return OamLinkStatusInfo;
	}
	public void setOamLinkStatusInfo(OamLinkStatusInfo oamLinkStatusInfo) {
		OamLinkStatusInfo = oamLinkStatusInfo;
	}
	public OamMepInst getOamMepInst() {
		return oamMepInst;
	}
	public void setOamMepInst(OamMepInst oamMepInst) {
		this.oamMepInst = oamMepInst;
	}
	public MacStudyBean getMacStudyBean() {
		return macStudyBean;
	}
	public void setMacStudyBean(MacStudyBean macStudyBean) {
		this.macStudyBean = macStudyBean;
	}
	
}
