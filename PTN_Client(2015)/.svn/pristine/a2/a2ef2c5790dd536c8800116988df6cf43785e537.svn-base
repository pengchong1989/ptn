package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public enum EActiveStatusUnLoad {
	ACTIVITY(0),UNACTIVITY(1);
	private int value;
	
	private EActiveStatusUnLoad(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EActiveStatusUnLoad forms(int value) {
		for(EActiveStatusUnLoad serviceType : EActiveStatusUnLoad.values()){
			if(value == serviceType.value)
				return serviceType;
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(value == 0)
			return ResourceUtil.srcStr(StringKeysObj.ACTIVITY_YES);
		else if(value == 1) 
			return ResourceUtil.srcStr(StringKeysObj.ACTIVITY_NO);
		else
			return null;
	}
}
