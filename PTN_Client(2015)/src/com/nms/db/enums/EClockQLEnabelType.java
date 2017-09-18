package com.nms.db.enums;


public enum EClockQLEnabelType implements IntEnum{


	Enable(0),Unabel(1);
	private int value;
	
	private EClockQLEnabelType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EClockQLEnabelType forms(int value) {
		for(EClockQLEnabelType pwType : EClockQLEnabelType.values()){
			if(value == pwType.value)
				return pwType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 0)
			return "使能";
		else if (value == 1)
			return "未使能";
		else
			return null;
	}
	public static void main(String args[]){
		System.out.println(EClockQLEnabelType.valueOf("PDH").getValue());
	}
}
