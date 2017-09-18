package com.nms.drive.service.bean;

/**
 * PW层缓存管理策略对象
 * 
 * @author hulei
 * 
 */
public class PwBufferManage {
	private int model = 0;// 队列缓存管理策略)(队列)模式
	private int startLimit = 0;// (队列)START门限
	private int endLimit = 0;// (队列)END门限

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getStartLimit() {
		return startLimit;
	}

	public void setStartLimit(int startLimit) {
		this.startLimit = startLimit;
	}

	public int getEndLimit() {
		return endLimit;
	}

	public void setEndLimit(int endLimit) {
		this.endLimit = endLimit;
	}

}
