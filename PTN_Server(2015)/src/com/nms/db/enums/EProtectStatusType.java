package com.nms.db.enums;

public enum EProtectStatusType {
	LOCK(0),SF_P(1),FS(2),FS_P(3),SF(4),SD_P(5),SD(6),MS(7),
	MS_P(8),WTR(9),EXER(10),LINKOAM(11),
	RR(12),DNR(13),NR(14),NR_P(15),CLEAR(16);
	private int value;
	private EProtectStatusType(int value) {
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public static EProtectStatusType from(int value) {
		for(EProtectStatusType eActionType : EProtectStatusType.values()){
			if(value == eActionType.value)
				return eActionType;
		}
		return null;
	}
	@Override
	public String toString() {
		if(this.value == 0){
			return "LOCK";
		}else if(this.value == 1){
			return "SF_P";
		}else if(this.value == 2){
			return "FS";
		}else if(this.value == 3){
			return "FS_P";
		}else if(this.value == 4){
			return "SF";
		}else if(this.value == 5){
			return "SD_P";
		}else if(this.value == 6){
			return "SD";
		}else if(this.value == 7){
			return "MS";
		}else if(this.value == 8){
			return "MS_P";
		}else if(this.value == 9){
			return "WTR";
		}else if(this.value == 10){
			return "EXER";
		}else if(this.value == 11){
			return "LINKOAM";
		}else if(this.value == 12){
			return "RR";
		}else if(this.value == 13){
			return "DNR";
		}else if(this.value == 14){
			return "NR";
		}else if(this.value == 15){
			return "NR_P";
		}else if(this.value == 16){
			return "CLEAR";
		}
		return "";
	}
}
