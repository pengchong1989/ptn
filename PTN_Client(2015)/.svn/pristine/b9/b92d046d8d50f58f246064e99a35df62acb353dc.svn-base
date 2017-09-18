package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;


public enum EPortLagMode implements IntEnum{


	TYPE_NO(0),ACCORDING_SOURCE_MACADDRESS(1),ACCORDING_TARGET_MACADDRESS(2),
	ACCORDING_SOURCE_TARGET_MACADDRESS(3),ACCORDING_SOURCE_IP(4),ACCORDING_TARGET_IP(5),ACCORDING_SOURCE_TARGET_IP(6);
	private int value;
	
	private EPortLagMode(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EPortLagMode forms(int value) {
		for(EPortLagMode type : EPortLagMode.values()){
			if(value == type.value)
				return type;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 0)
			return ResourceUtil.srcStr(StringKeysObj.LSP_TYPE_NO);
		else if (value == 1)
			return ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_SOURCE_MACADDRESS);
		else if (value == 2)
			return ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_TARGET_MACADDRESS);
		else if (value == 3)
			return ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_SOURCE_TARGET_MACADDRESS);
		else if (value == 4)
			return ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_SOURCE_IP);
		else if(value == 5)
			return ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_TARGET_IP);
		else if(value == 6)
			return ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_SOURCE_TARGET_IP);
		else
			return null;
	}
}
