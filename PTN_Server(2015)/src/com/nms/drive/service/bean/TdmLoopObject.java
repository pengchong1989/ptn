package com.nms.drive.service.bean;

public class TdmLoopObject {
	private int loopType = 0;// 0/1 线路环回/设备环回
	private int switchStatus = 0;// 0/1 关/开
	private int legId = 0;// 支路id 00/01/.../04 =支路1/支路2/支路3/支路4/全部链路
	private int siteId = 0;
	public int getLoopType() {
		return loopType;
	}
	public void setLoopType(int loopType) {
		this.loopType = loopType;
	}
	public int getSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(int switchStatus) {
		this.switchStatus = switchStatus;
	}
	public int getLegId() {
		return legId;
	}
	public void setLegId(int legId) {
		this.legId = legId;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
}
