package com.nms.corba.ninterface.enums;

public enum ERotateStatus {
	IDLE(1),ROTATING(2),ROTATED(3);
	private int value;
	private ERotateStatus(int value) {
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public ERotateStatus from(int value) {
		for(ERotateStatus eRotateStatus : ERotateStatus.values()){
			if(value == eRotateStatus.value)
				return eRotateStatus;
		}
		return null;
	}
	@Override
	public String toString() {
		if(this.value == 1){
			return "IDLE";
		}else if(this.value == 2){
			return "ROTATING";
		}else if(this.value == 3){
			return "ROTATED";
		}
		return "";
	}
}
