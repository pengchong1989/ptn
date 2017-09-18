package com.nms.drive.service.bean;

/**
 * 
 * AlarmInfoObject 告警信息
 * @author 彭冲
 *
 */
public class AlarmInfoObject {

	private int alarmCode = 0;// 告警代码
	private int alarmStatus = 0;// 告警状态
	private String alarmDate = "";// 年-月-日 时:分:秒
	private String endAlarmDate = "";//告警结束时间
	
	public String getEndAlarmDate() {
		return endAlarmDate;
	}

	public void setEndAlarmDate(String endAlarmDate) {
		this.endAlarmDate = endAlarmDate;
	}

	public int getAlarmCode() {
		return alarmCode;
	}

	public void setAlarmCode(int alarmCode) {
		this.alarmCode = alarmCode;
	}

	public int getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(int alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	public String getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(String alarmDate) {
		this.alarmDate = alarmDate;
	}

}
