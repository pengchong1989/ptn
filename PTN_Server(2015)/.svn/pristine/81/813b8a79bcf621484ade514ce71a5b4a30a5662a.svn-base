package com.nms.corba.ninterface.enums;

public enum ESwitchDirection {
	WORK(1),PROTECT(2);
	private int value;
	private ESwitchDirection(int value) {
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public ESwitchDirection from(int value) {
		for(ESwitchDirection eSwitchDirection : ESwitchDirection.values()){
			if(value == eSwitchDirection.value)
				return eSwitchDirection;
		}
		return null;
	}
	@Override
	public String toString() {
		if(this.value == 1){
			return "WORK";
		}else if(this.value == 2){
			return "PROTECT";
		}
		return "";
	}
}
