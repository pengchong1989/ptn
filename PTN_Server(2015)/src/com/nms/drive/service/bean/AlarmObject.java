package com.nms.drive.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * AlarmObject所有告警
 * @author 彭冲
 *
 */
public class AlarmObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3165376226282211638L;
	private int neAddress = 0;// NE地址
	private int neFeature = 0;// NE特征字
	private int alarmMasterCardCount = 0;// 告警盘的总数
	private List<AlarmMasterCardObject> alarmMasterCardObjectList = new ArrayList<AlarmMasterCardObject>();
	private String neIP;//网元IP
	
	public int getAlarmMasterCardCount() {
		return alarmMasterCardCount;
	}

	public void setAlarmMasterCardCount(int alarmMasterCardCount) {
		this.alarmMasterCardCount = alarmMasterCardCount;
	}

	public int getNeAddress() {
		return neAddress;
	}

	public void setNeAddress(int neAddress) {
		this.neAddress = neAddress;
	}

	public int getNeFeature() {
		return neFeature;
	}

	public void setNeFeature(int neFeature) {
		this.neFeature = neFeature;
	}

	public List<AlarmMasterCardObject> getAlarmMasterCardObjectList() {
		return alarmMasterCardObjectList;
	}

	public void setAlarmMasterCardObjectList(List<AlarmMasterCardObject> alarmMasterCardObjectList) {
		this.alarmMasterCardObjectList = alarmMasterCardObjectList;
	}

	public String getNeIP() {
		return neIP;
	}

	public void setNeIP(String neIP) {
		this.neIP = neIP;
	}

	
}
