package com.nms.db.bean.equipment.port;

import java.io.Serializable;

import com.nms.db.enums.ETdmLoopLineType;
import com.nms.db.enums.ETdmLoopType;

public class TdmLoopInfo implements Serializable{
	private static final long serialVersionUID = -5073430932404800336L;
	private String siteName;
	private ETdmLoopType tmdtype;
	private int loopType;// 0/1 线路环回/设备环回	
	private int legId;// 支路id 00/01/.../04 =支路1/支路2/支路3/支路4/全部链路
	private int siteId;
	private ETdmLoopLineType tdmLegId;
	private int switchStatus;// 0/1 关/开
	private int e1Id;
	public int getE1Id() {
		return e1Id;
	}
	public void setE1Id(int e1Id) {
		this.e1Id = e1Id;
	}
	public ETdmLoopType getTmdType() {
		return tmdtype;
	}
	public void setTmdType(ETdmLoopType type) {
		this.tmdtype = type;
	}
	public ETdmLoopLineType getTdmLegId() {
		return tdmLegId;
	}
	public void setTdmLegId(ETdmLoopLineType tdmLegId) {
		this.tdmLegId = tdmLegId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
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
