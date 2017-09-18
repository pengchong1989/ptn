package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 告警屏蔽
 * @author Administrator
 */
public class AlarmShieldObject {
	
	private int lineOrAlarmCode = 1;//用于区分是 告警屏蔽还是线路屏蔽 1:告警屏蔽；2线路屏蔽
	private int treatyNumber = 1;
	private int neShield = 0;//单盘屏蔽配置 (1个字节):表示 是否屏蔽单盘的所有告警（性能,线路） 0表示不屏蔽，0x01 表示屏蔽;当选择屏蔽时,此字节后面的配置数据部不处理
                          //但保留，当选项不屏蔽时，屏蔽配置由后面的配置数据决定。
	private List<AlarmShieldType> alarmShieldTypeList = new ArrayList<AlarmShieldType>();
	 private int shieldType = 1;//屏蔽配置条目的格式 0x01:表示先列出"线路代码"，在列出屏蔽代码；0x02:先列出屏蔽代码在列出告警线路
	 
	public int getNeShield() {
		return neShield;
	}
	public void setNeShield(int neShield) {
		this.neShield = neShield;
	}
	public List<AlarmShieldType> getAlarmShieldTypeList() {
		return alarmShieldTypeList;
	}
	public void setAlarmShieldTypeList(List<AlarmShieldType> alarmShieldTypeList) {
		this.alarmShieldTypeList = alarmShieldTypeList;
	}
	public int getTreatyNumber() {
		return treatyNumber;
	}
	public void setTreatyNumber(int treatyNumber) {
		this.treatyNumber = treatyNumber;
	}
	public int getLineOrAlarmCode() {
		return lineOrAlarmCode;
	}
	public void setLineOrAlarmCode(int lineOrAlarmCode) {
		this.lineOrAlarmCode = lineOrAlarmCode;
	}
	public int getShieldType() {
		return shieldType;
	}
	public void setShieldType(int shieldType) {
		this.shieldType = shieldType;
	}

}
