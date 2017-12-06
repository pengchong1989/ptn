package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

/**
 * 监控周期
 * @author lp
 *
 */
public enum EMonitorCycle implements IntEnum{
	MIN15(1,15*60*1000),HOUR24(2,24*60*60*1000),M50(3,50*1000),MIN10(4,10*60*1000);
	private int value;
	private long interval;
	private EMonitorCycle(int value,long interval) {
		this.value = value;
		this.interval = interval;
	}
	public int getValue() {
		return value;
	}
	
	public long getInterval() {
		return interval;
	}
	public static EMonitorCycle forms(int value) {
		for(EMonitorCycle serviceType : EMonitorCycle.values()){
			if(value == serviceType.value)
				return serviceType;
		}
		return null;
	}
	
	public static EMonitorCycle forms(long interval) {
		for(EMonitorCycle serviceType : EMonitorCycle.values()){
			if(interval == serviceType.interval)
				return serviceType;
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(value == 1)
			return ResourceUtil.srcStr(StringKeysObj.OBJ_15_MINUTES);
		else if(value == 2) 
			return ResourceUtil.srcStr(StringKeysObj.OBJ_24_HOURS);
		else
			return null;
	}
}
