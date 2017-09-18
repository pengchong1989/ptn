package com.nms.db.enums;


public enum EClockModelType implements IntEnum{


	AUTO(0),HOLD(1),FREE(2);
	private int value;
	
	private EClockModelType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EClockModelType forms(int value) {
		for(EClockModelType pwType : EClockModelType.values()){
			if(value == pwType.value)
				return pwType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 0)
			return "AUTO";
		else if (value == 1)
			return "HOLD";
		else if (value == 2)
			return "FREE";
		else
			return null;
	}
	public static void main(String args[]){
		System.out.println(EClockModelType.valueOf("PDH").getValue());
	}
}
