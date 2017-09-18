package com.nms.db.enums;


public enum ECycleType implements IntEnum{


	THREEPOINT33_MS(1),TEN_MS(10),HUNDRED_MS(11),ONE_S(100);
	private int value;
	
	private ECycleType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static ECycleType forms(int value) {
		for(ECycleType type : ECycleType.values()){
			if(value == type.value)
				return type;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 1)
			return "3.33ms";
		else if (value == 10)
			return "10ms";
		else if (value == 11)
			return "100ms";
		else if (value == 100)
			return "1s";
		else
			return null;
	}
}
