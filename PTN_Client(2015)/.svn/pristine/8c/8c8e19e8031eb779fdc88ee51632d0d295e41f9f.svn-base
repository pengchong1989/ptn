package com.nms.db.enums;


public enum EOutClockSelectType implements IntEnum{


	HDB3(0),Hz(1);
	private int value;
	
	private EOutClockSelectType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EOutClockSelectType forms(int value) {
		for(EOutClockSelectType pwType : EOutClockSelectType.values()){
			if(value == pwType.value)
				return pwType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 0)
			return "HDB3";
		else if (value == 1)
			return "Hz";
		else
			return null;
	}
	public static void main(String args[]){
		System.out.println(EOutClockSelectType.valueOf("PDH").getValue());
	}
}
