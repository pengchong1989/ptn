package com.nms.db.enums;


public enum ETdmLoopType implements IntEnum{


	LINE(0),EQUIPMENT(1);
	private int value;
	
	private ETdmLoopType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static ETdmLoopType forms(int value) {
		for(ETdmLoopType pwType : ETdmLoopType.values()){
			if(value == pwType.value)
				return pwType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 0)
			return "线路环回";
		else if (value == 1)
			return "设备环回";
		else
			return null;
	}
	public static void main(String args[]){
		System.out.println(ETdmLoopType.valueOf("PDH").getValue());
	}
}
