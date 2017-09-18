package com.nms.drive.service.bean;

public class ProtectRorateObject {
	private int recoverMain;//恢复到主用
	private int forceStand;//强制到备用
	private int lockMain;//锁定在主用
	private int manpowerStand;//人工倒换到备用
	private int clear;//清除
	private int roratePractise;//倒换练习
	private int siteRorate;//网元倒换
	private int tunnelId;//
	private int tunnelbusinessid;
	private int roate;
	private int pwbussinessid;
	private String roateType;
	public String getRoateType() {
		return roateType;
	}
	public void setRoateType(String roateType) {
		this.roateType = roateType;
	}
	public int getPwbussinessid() {
		return pwbussinessid;
	}
	public void setPwbussinessid(int pwbussinessid) {
		this.pwbussinessid = pwbussinessid;
	}
	public int getRecoverMain() {
		return recoverMain;
	}
	public void setRecoverMain(int recoverMain) {
		this.recoverMain = recoverMain;
	}
	public int getForceStand() {
		return forceStand;
	}
	public void setForceStand(int forceStand) {
		this.forceStand = forceStand;
	}
	public int getLockMain() {
		return lockMain;
	}
	public void setLockMain(int lockMain) {
		this.lockMain = lockMain;
	}
	public int getManpowerStand() {
		return manpowerStand;
	}
	public void setManpowerStand(int manpowerStand) {
		this.manpowerStand = manpowerStand;
	}
	public int getClear() {
		return clear;
	}
	public void setClear(int clear) {
		this.clear = clear;
	}
	public int getRoratePractise() {
		return roratePractise;
	}
	public void setRoratePractise(int roratePractise) {
		this.roratePractise = roratePractise;
	}
	public int getSiteRorate() {
		return siteRorate;
	}
	public void setSiteRorate(int siteRorate) {
		this.siteRorate = siteRorate;
	}
	public int getTunnelId() {
		return tunnelId;
	}
	public void setTunnelId(int tunnelId) {
		this.tunnelId = tunnelId;
	}
	public int getTunnelbusinessid() {
		return tunnelbusinessid;
	}
	public void setTunnelbusinessid(int tunnelbusinessid) {
		this.tunnelbusinessid = tunnelbusinessid;
	}
	public int getRoate() {
		return roate;
	}
	public void setRoate(int roate) {
		this.roate = roate;
	}
	
}
