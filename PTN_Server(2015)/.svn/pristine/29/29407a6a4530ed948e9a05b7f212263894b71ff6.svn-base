package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public enum ECheckStatus {
	NOCHECK(0),CHECK(1);
	private int value;
	
	private ECheckStatus(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static ECheckStatus forms(int value) {
		for(ECheckStatus eCheckStatus : ECheckStatus.values()){
			if(value == eCheckStatus.value)
				return eCheckStatus;
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(value == 0)
			return ResourceUtil.srcStr(StringKeysObj.CHECKING_NO);
		else if(value == 1) 
			return ResourceUtil.srcStr(StringKeysObj.CHECKING_YES);
		else
			return null;
	}
}
