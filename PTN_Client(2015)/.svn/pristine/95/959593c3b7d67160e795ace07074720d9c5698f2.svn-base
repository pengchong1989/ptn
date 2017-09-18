package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;


public enum EClockQLType implements IntEnum{


	QL(0),QL1(2),QL2(4),QL3(8),QL4(11),QL5(15),QL6(31);
	private int value;
	
	private EClockQLType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EClockQLType forms(int value) {
		for(EClockQLType pwType : EClockQLType.values()){
			if(value == pwType.value)
				return pwType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 0)
			return ResourceUtil.srcStr(StringKeysObj.QUALITY_UNKNOWN);
		else if (value == 2)
			return ResourceUtil.srcStr(StringKeysObj.G811_CLOCK);
		else if (value == 4)
			return ResourceUtil.srcStr(StringKeysObj.G812_PASS_CLOCK);
		else if (value == 8)
			return ResourceUtil.srcStr(StringKeysObj.G813_NATIVE_CLOCK);
		else if (value == 11)
			return ResourceUtil.srcStr(StringKeysObj.G813_CLOCK);
		else if (value == 15)
			return ResourceUtil.srcStr(StringKeysObj.SYNCHRO_NO);
		else if (value == 31)
			return ResourceUtil.srcStr(StringKeysObj.AUTO_S1);
		else
			return null;
	}
	public static void main(String args[]){
		System.out.println(EClockQLType.valueOf("PDH").getValue());
	}
}
