package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public enum EActiveStatus {
	NONE(0),ACTIVITY(1),UNACTIVITY(2);
	private int value;
	
	private EActiveStatus(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EActiveStatus forms(int value) {
		for(EActiveStatus serviceType : EActiveStatus.values()){
			if(value == serviceType.value)
				return serviceType;
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(value == 1)
			return ResourceUtil.srcStr(StringKeysObj.ACTIVITY_YES);
		else if(value == 2) 
			return ResourceUtil.srcStr(StringKeysObj.ACTIVITY_NO);
		else
			return null;
	}
}
