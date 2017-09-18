package com.nms.db.enums;


public enum ETdmLoopLineType implements IntEnum{


	LINE1(0),LINE2(1),LINE3(2),LINE4(3),ALLLINE(4);
	private int value;
	
	private ETdmLoopLineType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static ETdmLoopLineType forms(int value) {
		for(ETdmLoopLineType pwType : ETdmLoopLineType.values()){
			if(value == pwType.value)
				return pwType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 0)
			return "支路一";
		else if (value == 1)
			return "支路二";
		else if (value == 2)
			return "支路三";
		else if (value == 3)
			return "支路四";
		else if (value == 4)
			return "全部链路";
		else
			return null;
	}
	public static void main(String args[]){
		System.out.println(ETdmLoopLineType.valueOf("PDH").getValue());
	}
}
