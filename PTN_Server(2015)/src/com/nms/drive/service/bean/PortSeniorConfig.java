package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 端口高级配置
 * @author 彭冲
 *
 */
public class PortSeniorConfig {
	
	private String priority = "0,1,2,3,4,5,6,7";//优先级 (0)/1/2/3/4/5/6/7=(BE)/AF1/AF2/AF3/AF4/EF/CS6/CS7
	private List<PortLAGexitQueue> exitQueueList = new ArrayList<PortLAGexitQueue>();// (出口队列调度策略)
	private List<PortLAGbuffer> bufferList = new ArrayList<PortLAGbuffer>();//(队列缓存管理策略)
	private int portLimitation = 1000000;// 端口出口限速 
	private int broadcastBate = 0;// 广播包抑制 (0)/1=(关)/开 
	private int broadcastFlux = 0;// 广播包流量 
	private int groupBroadcastBate =0;// 组播包抑制 
	private int groupBroadcastFlux = 0;// 组播包流量 
	private int floodBate = 0;// 洪泛包抑制 (0)/1=(关)/开 
	private int floodFlux = 0;// 洪泛包流量 
	private String pri_Priority = "0 1 2 3 4 5 6 7";//端口pri映射
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
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
	public String getPri_Priority() {
		return pri_Priority;
	}
	public void setPri_Priority(String priPriority) {
		pri_Priority = priPriority;
	}
	
}
