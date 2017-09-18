package com.nms.service.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.nms.drive.service.bean.AlarmObject;

/**
 * 主动上报告警
 * @author sy
 *
 */
public class AlarmObjectService {
	
	private List<AlarmObject> alarmObject_wh=new ArrayList<AlarmObject>();
	private List<com.nms.drivechenxiao.service.bean.alarm.AlarmObject> alarmObject_cx=new ArrayList<com.nms.drivechenxiao.service.bean.alarm.AlarmObject>();
	private ConcurrentHashMap<String,byte[]> concurrentHashMap = new ConcurrentHashMap<String,byte[]>(16);//轮询设备告警的存储对象
	public List<AlarmObject> getAlarmObject_wh() {
		return alarmObject_wh;
	}
	public void setAlarmObject_wh(List<AlarmObject> alarmObject_wh) {
		this.alarmObject_wh = alarmObject_wh;
	}
	public List<com.nms.drivechenxiao.service.bean.alarm.AlarmObject> getAlarmObject_cx() {
		return alarmObject_cx;
	}
	public void setAlarmObject_cx(List<com.nms.drivechenxiao.service.bean.alarm.AlarmObject> alarmObject_cx) {
		this.alarmObject_cx = alarmObject_cx;
	}
	public ConcurrentHashMap<String, byte[]> getConcurrentHashMap() {
		return concurrentHashMap;
	}
	public void setConcurrentHashMap(ConcurrentHashMap<String, byte[]> concurrentHashMap) {
		this.concurrentHashMap = concurrentHashMap;
	}
	
}
