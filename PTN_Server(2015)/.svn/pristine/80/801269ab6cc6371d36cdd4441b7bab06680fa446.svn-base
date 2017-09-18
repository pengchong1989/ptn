package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;
/**
 * 端口聚合对象
 * @author 罗磊
 *
 */
public class PortLAGObject {

	private int lagID = 0;// LAG ID
	private int lagMode = 0;// 聚合模式0/(1)/2/3=关/(基于源MAC)/基于目的MAC/基于源和目的MAC
	
	private List<PortLAGMember> memberList = new ArrayList<PortLAGMember>();//聚合成员端口,只有四个 
	private int portEnable = 0;// 非负载分担返回模式
	private int flowControl = 0;// 主聚合成员端口
	private int mtu = 0;		// 等待恢复时间
	private int vlanRelating = 0;// VLAN关联设置 (0)/1=(不关联)/关联
	private int relatingSet = 0;// 802.1P关联设置 (0)/1=(不关联)/关联 
	private int fountainMAC = 0;// 源MAC地址关联设置 (0)/1=(不关联)/关联 
	private int aimMAC = 0;// 目的MAC地址关联设置 (0)/1=(不关联)/关联 
	private int fountainIP = 0;// 源IP地址关联设置 (0)/1=(不关联)/关联 
	private int aimIP = 0;// 目的IP地址关联设置 (0)/1=(不关联)/关联 
	private int pwSet = 0;// PW关联设置 (0)/1=(不关联)/关联 
	private int ipDSCPSet = 0;// IP DSCP关联设置 (0)/1=(不关联)/关联 
	
	private String pri = "0,1,2,3,4,5,6,7";// 优先级 (0)/1/2/3/4/5/6/7=(BE)/AF1/AF2/AF3/AF4/EF/CS6/CS7
	private List<PortLAGexitQueue> exitQueueList = new ArrayList<PortLAGexitQueue>();// (出口队列调度策略)
	private List<PortLAGbuffer> bufferList = new ArrayList<PortLAGbuffer>();//(队列缓存管理策略)
	
	private int portLimitation = 0;// 端口出口限速 
	private int broadcastBate = 0;// 聚合组工作模式
	private int broadcastFlux = 0;// 广播包流量 
	private int groupBroadcastBate =0;// 聚合组工作模式
	private int groupBroadcastFlux = 0;// 组播包流量 
	private int floodBate = 0;// 从聚合成员端口
	private int floodFlux = 0;// 洪泛包流量 
	private int reserve = 0;// 备用,00 
	private int mainSlot=0;//主槽位号
	private int standSlot=0;//从槽位号
	public int getLagID() {
		return lagID;
	}
	public void setLagID(int lagID) {
		this.lagID = lagID;
	}
	public int getLagMode() {
		return lagMode;
	}
	public void setLagMode(int lagMode) {
		this.lagMode = lagMode;
	}


	public int getPortEnable() {
		return portEnable;
	}
	public void setPortEnable(int portEnable) {
		this.portEnable = portEnable;
	}
	public int getFlowControl() {
		return flowControl;
	}
	public void setFlowControl(int flowControl) {
		this.flowControl = flowControl;
	}
	public int getMtu() {
		return mtu;
	}
	public void setMtu(int mtu) {
		this.mtu = mtu;
	}
	public int getVlanRelating() {
		return vlanRelating;
	}
	public void setVlanRelating(int vlanRelating) {
		this.vlanRelating = vlanRelating;
	}
	public int getRelatingSet() {
		return relatingSet;
	}
	public void setRelatingSet(int relatingSet) {
		this.relatingSet = relatingSet;
	}
	public int getFountainMAC() {
		return fountainMAC;
	}
	public void setFountainMAC(int fountainMAC) {
		this.fountainMAC = fountainMAC;
	}
	public int getAimMAC() {
		return aimMAC;
	}
	public void setAimMAC(int aimMAC) {
		this.aimMAC = aimMAC;
	}
	public int getFountainIP() {
		return fountainIP;
	}
	public void setFountainIP(int fountainIP) {
		this.fountainIP = fountainIP;
	}
	public int getAimIP() {
		return aimIP;
	}
	public void setAimIP(int aimIP) {
		this.aimIP = aimIP;
	}
	public int getPwSet() {
		return pwSet;
	}
	public void setPwSet(int pwSet) {
		this.pwSet = pwSet;
	}
	public int getIpDSCPSet() {
		return ipDSCPSet;
	}
	public void setIpDSCPSet(int ipDSCPSet) {
		this.ipDSCPSet = ipDSCPSet;
	}
	public String getPri() {
		return pri;
	}
	public void setPri(String pri) {
		this.pri = pri;
	}
	public List<PortLAGMember> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<PortLAGMember> memberList) {
		this.memberList = memberList;
	}
	public List<PortLAGexitQueue> getExitQueueList() {
		return exitQueueList;
	}
	public void setExitQueueList(List<PortLAGexitQueue> exitQueueList) {
		this.exitQueueList = exitQueueList;
	}
	public List<PortLAGbuffer> getBufferList() {
		return bufferList;
	}
	public void setBufferList(List<PortLAGbuffer> bufferList) {
		this.bufferList = bufferList;
	}
	public int getPortLimitation() {
		return portLimitation;
	}
	public void setPortLimitation(int portLimitation) {
		this.portLimitation = portLimitation;
	}
	public int getBroadcastBate() {
		return broadcastBate;
	}
	public void setBroadcastBate(int broadcastBate) {
		this.broadcastBate = broadcastBate;
	}
	public int getBroadcastFlux() {
		return broadcastFlux;
	}
	public void setBroadcastFlux(int broadcastFlux) {
		this.broadcastFlux = broadcastFlux;
	}
	public int getGroupBroadcastBate() {
		return groupBroadcastBate;
	}
	public void setGroupBroadcastBate(int groupBroadcastBate) {
		this.groupBroadcastBate = groupBroadcastBate;
	}
	public int getGroupBroadcastFlux() {
		return groupBroadcastFlux;
	}
	public void setGroupBroadcastFlux(int groupBroadcastFlux) {
		this.groupBroadcastFlux = groupBroadcastFlux;
	}
	public int getFloodBate() {
		return floodBate;
	}
	public void setFloodBate(int floodBate) {
		this.floodBate = floodBate;
	}
	public int getFloodFlux() {
		return floodFlux;
	}
	public void setFloodFlux(int floodFlux) {
		this.floodFlux = floodFlux;
	}
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public int getMainSlot() {
		return mainSlot;
	}
	public void setMainSlot(int mainSlot) {
		this.mainSlot = mainSlot;
	}
	public int getStandSlot() {
		return standSlot;
	}
	public void setStandSlot(int standSlot) {
		this.standSlot = standSlot;
	}
	
	

}
