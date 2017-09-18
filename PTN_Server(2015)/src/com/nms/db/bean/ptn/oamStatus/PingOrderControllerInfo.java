package com.nms.db.bean.ptn.oamStatus;


public class PingOrderControllerInfo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6751040571484288965L;
	private int siteId;
	private int id;
	private int mepId;
	private String farMac;
	private int ping;
	private int type;
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMepId() {
		return mepId;
	}
	public void setMepId(int mepId) {
		this.mepId = mepId;
	}
	public String getFarMac() {
		return farMac;
	}
	public void setFarMac(String farMac) {
		this.farMac = farMac;
	}
	public int getPing() {
		return ping;
	}
	public void setPing(int ping) {
		this.ping = ping;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
