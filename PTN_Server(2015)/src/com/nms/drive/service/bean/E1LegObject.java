package com.nms.drive.service.bean;

public class E1LegObject {

	private int legShield = 0;// 2M支路屏蔽(00)/01=(不屏蔽)/屏蔽
	private int legEnable = 0;// 2M支路使能00/(01)= 不使能/(使能)
	private int legId = 0;// 2M支路ID
	private int prestoreTimeEnable = 0;// 缓存时间控制使能(00)/01=(不使能)/使能
	private int prestoreTime = 0;// 缓存时间 4(取值范围3~7) 单位：毫秒
	private int pinCount = 0;// 封装帧个数4(取值范围1/2/4/8)
	private int reserve = 0;// 备用(00)7：备用1(00)…备用7(00)
	private	int pwLable = 0;//pw标签

	public int getPwLable() {
		return pwLable;
	}

	public void setPwLable(int pwLable) {
		this.pwLable = pwLable;
	}

	public int getLegShield() {
		return legShield;
	}

	public void setLegShield(int legShield) {
		this.legShield = legShield;
	}

	public int getLegEnable() {
		return legEnable;
	}

	public void setLegEnable(int legEnable) {
		this.legEnable = legEnable;
	}

	public int getLegId() {
		return legId;
	}

	public void setLegId(int legId) {
		this.legId = legId;
	}

	public int getPrestoreTimeEnable() {
		return prestoreTimeEnable;
	}

	public void setPrestoreTimeEnable(int prestoreTimeEnable) {
		this.prestoreTimeEnable = prestoreTimeEnable;
	}

	public int getPrestoreTime() {
		return prestoreTime;
	}

	public void setPrestoreTime(int prestoreTime) {
		this.prestoreTime = prestoreTime;
	}

	public int getPinCount() {
		return pinCount;
	}

	public void setPinCount(int pinCount) {
		this.pinCount = pinCount;
	}

	public int getReserve() {
		return reserve;
	}

	public void setReserve(int reserve) {
		this.reserve = reserve;
	}

}
