package com.nms.drive.service.bean.status;

public class OamLinkStatusInfoObject {

	private int portNumber ;//本端OAM端口= 0/1/…/19H=LAN1/LAN2/…/LAN22/WAN1/WAN2/WAN3/WAN4
	private int oamConsult ;// 本端OAM协商态=0/1/2/3/4=LINK_FAULT/WAIE/ACTIVE/SEND/ANY
	private int oamLinkStatus ;//本端OAM链路状态(stLocalVar.bLinkOper)=0/1=未连接/连接
	private int oamPortFaultStatus ;//本端OAM端口fault状态=0/1=否/是
	private int loopFailfalse ; //本端环回命令超时失败=0/1=否/是
	private int loopAnalysisStatus ; //本端环回解析状态(stLocalInfo.eParserAction)=0/1/2= none/环回/丢弃
	private int remoteLoopAnalysisStatus ; //远端环回解析状态(stLocalInfo.eParserAction)=0/1/2= none/环回/丢弃
	
	public int getRemoteLoopAnalysisStatus() {
		return remoteLoopAnalysisStatus;
	}
	public void setRemoteLoopAnalysisStatus(int remoteLoopAnalysisStatus) {
		this.remoteLoopAnalysisStatus = remoteLoopAnalysisStatus;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public int getOamConsult() {
		return oamConsult;
	}
	public void setOamConsult(int oamConsult) {
		this.oamConsult = oamConsult;
	}
	public int getOamLinkStatus() {
		return oamLinkStatus;
	}
	public void setOamLinkStatus(int oamLinkStatus) {
		this.oamLinkStatus = oamLinkStatus;
	}
	public int getOamPortFaultStatus() {
		return oamPortFaultStatus;
	}
	public void setOamPortFaultStatus(int oamPortFaultStatus) {
		this.oamPortFaultStatus = oamPortFaultStatus;
	}
	public int getLoopFailfalse() {
		return loopFailfalse;
	}
	public void setLoopFailfalse(int loopFailfalse) {
		this.loopFailfalse = loopFailfalse;
	}
	public int getLoopAnalysisStatus() {
		return loopAnalysisStatus;
	}
	public void setLoopAnalysisStatus(int loopAnalysisStatus) {
		this.loopAnalysisStatus = loopAnalysisStatus;
	}
}
