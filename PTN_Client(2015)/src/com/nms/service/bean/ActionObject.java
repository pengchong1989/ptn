package com.nms.service.bean;

import com.nms.drive.service.bean.AlarmObject;
import com.nms.drive.service.bean.NEObject;

/**
 * 武汉驱动事件对象
 * @author Administrator
 *
 */
public class ActionObject {

	private int actionId = 0;//操作ID
	private String status = "";//设备返回值
	private NEObject neObject = new NEObject();
	private int servicetType = 0;//类型 tunnel pw等
	private String type = "";// 类型 tunnel pw等
	private long performanceBeginDataTime;// （起始时间）年，年，月，日，时，分，秒
	private int performanceCount = 0;// 历史性能个数
	private int performanceBeginCount = 0;// 历史性能起始数
	private int performanceType = 0;// 性能数据类型（00，20，21，30）
	private AlarmObject alarmObject = new AlarmObject();
	private byte[] bs ;
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public NEObject getNeObject() {
		return neObject;
	}
	public void setNeObject(NEObject neObject) {
		this.neObject = neObject;
	}
	public int getServicetType() {
		return servicetType;
	}
	public void setServicetType(int servicetType) {
		this.servicetType = servicetType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getPerformanceBeginDataTime() {
		return performanceBeginDataTime;
	}
	public void setPerformanceBeginDataTime(long performanceBeginDataTime) {
		this.performanceBeginDataTime = performanceBeginDataTime;
	}
	public int getPerformanceCount() {
		return performanceCount;
	}
	public void setPerformanceCount(int performanceCount) {
		this.performanceCount = performanceCount;
	}
	public int getPerformanceBeginCount() {
		return performanceBeginCount;
	}
	public void setPerformanceBeginCount(int performanceBeginCount) {
		this.performanceBeginCount = performanceBeginCount;
	}
	public int getPerformanceType() {
		return performanceType;
	}
	public void setPerformanceType(int performanceType) {
		this.performanceType = performanceType;
	}
	public AlarmObject getAlarmObject() {
		return alarmObject;
	}
	public void setAlarmObject(AlarmObject alarmObject) {
		this.alarmObject = alarmObject;
	}
	public byte[] getBs() {
		return bs;
	}
	public void setBs(byte[] bs) {
		this.bs = bs;
	}
}
