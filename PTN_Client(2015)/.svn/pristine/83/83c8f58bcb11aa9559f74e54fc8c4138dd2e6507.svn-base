package com.nms.db.enums;


public enum EPwType implements IntEnum{


	NONE(0),ETH(1),PDH(2),SDH(3),PDH_SDH(4),SDH_PDH(5);
	private int value;
	
	private EPwType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EPwType forms(int value) {
		for(EPwType pwType : EPwType.values()){
			if(value == pwType.value)
				return pwType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 0)
			return "NONE";
		else if (value == 1)
			return "ETH";
		else if (value == 2)
			return "PDH";
		else if (value == 3)
			return "SDH";
		else if (value == 4)
			return "PDH-SDH";
		else if (value == 5)
			return "SDH-PDH";
		else
			return null;
	}
	public static void main(String args[]){
		System.out.println(EPwType.valueOf("PDH").getValue());
	}
}
