package com.nms.db.enums;

public enum EActionType {
	INSERT(0),UPDATE(1),DELETE(2);
	private int value;
	private EActionType(int value) {
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public EActionType from(int value) {
		for(EActionType eActionType : EActionType.values()){
			if(value == eActionType.value)
				return eActionType;
		}
		return null;
	}
	@Override
	public String toString() {
		if(this.value == 0){
			return "insert";
		}else if(this.value == 1){
			return "update";
		}else if(this.value == 2){
			return "delete";
		}
		return "";
	}
}
