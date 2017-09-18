package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTab;

public enum EActiveStatusUnLoadType {
	ACTIVITY(0),UNACTIVITY(1);
	private int value;
	
	private EActiveStatusUnLoadType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EActiveStatusUnLoadType forms(int value) {
		for(EActiveStatusUnLoadType serviceType : EActiveStatusUnLoadType.values()){
			if(value == serviceType.value)
				return serviceType;
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(value == 1)
			return ResourceUtil.srcStr(StringKeysBtn.BTN_HISTORY_ALARM);
		else if(value == 2) 
			return ResourceUtil.srcStr(StringKeysTab.TAB_HISPERFOR);
		else if(value == 3) 
			return ResourceUtil.srcStr(StringKeysBtn.BTN_LOGIN_LOG);
		else if(value == 4) 
			return ResourceUtil.srcStr(StringKeysBtn.BTN_LOGIN_OPER_LOG);
		else
			return null;
	}
}
