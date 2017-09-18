package com.nms.drive.service.bean;
/**
 * 端口聚合 中的  队列缓存管理策略 List
 * @author 罗磊
 *
 */
public class PortLAGbuffer {
	private int mode = 0;	// (队列缓存管理策略)(队列0)模式 (0)/1=(队尾丢弃)/WRED 
	private int startThreshold = 50;// (队列0)START门限 
	private int endThreshold = 90;// (队列0)END门限 
	
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getStartThreshold() {
		return startThreshold;
	}
	public void setStartThreshold(int startThreshold) {
		this.startThreshold = startThreshold;
	}
	public int getEndThreshold() {
		return endThreshold;
	}
	public void setEndThreshold(int endThreshold) {
		this.endThreshold = endThreshold;
	}
	
	
}
