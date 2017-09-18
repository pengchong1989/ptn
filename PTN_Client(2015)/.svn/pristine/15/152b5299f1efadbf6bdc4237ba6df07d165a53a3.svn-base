package com.nms.db.enums;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;


public enum ESMClockType implements IntEnum{


	SM(0),SM1(2),SM2(4),SM3(8),SM4(11),SM5(15);
	private int value;
	
	private ESMClockType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static ESMClockType forms(int value) {
		for(ESMClockType pwType : ESMClockType.values()){
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
		else
			return null;
	}
	public static void main(String args[]){
		System.out.println(ESMClockType.valueOf("PDH").getValue());
	}
}
