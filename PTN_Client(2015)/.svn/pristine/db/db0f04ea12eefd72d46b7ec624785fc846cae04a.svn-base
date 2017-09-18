package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public enum EManagerStatus {
	NOENABLED(0),ENABLED(1);
	private int value;
	
	private EManagerStatus(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EManagerStatus forms(int value) {
		for(EManagerStatus eManagerStatus : EManagerStatus.values()){
			if(value == eManagerStatus.value)
				return eManagerStatus;
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(value == 0)
			return ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO);
		else if(value == 1) 
			return ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED);
		else
			return null;
	}
}
