package com.nms.drive.service;

public enum PtnServiceEnum {
	TUNNEL(1),LSP_PROTECT(2),CLOCK(3),ALLCONFIG(4),E1(5),ETREE(6),ELAN(7),ELINE(8),PWPROTECT(9),OAMPING(10),ETHLOOP(11),BLACKWHITEMAC(12),PW(13),
	MSPW(14),TIMESYNC(15),OSPFWH(16);
	private int value;
	
	private PtnServiceEnum(int value){
		this.value = value;
	}
	
	public static PtnServiceEnum forms(int value) {
		for (PtnServiceEnum ptnServiceEnum : PtnServiceEnum.values()) {
			if (value == ptnServiceEnum.value)
				return ptnServiceEnum;
		}
		return null;
	}
	
	public int getValue(){
		return value;
	}
	
	@Override
	public String toString() {
		if(this.value == 1){
			return "TUNNEL";
		}
		else if(this.value ==2){
			return "LSP_PROTECT";
		}else if(this.value ==3){
			return "CLOCK";
		}else if(this.value ==4){
			return "ALLCONFIG";
		}else if(this.value ==5){
			return "E1";
		}else if(this.value ==6){
			return "ETREE";
		}else if(this.value ==7){
			return "ELAN";
		}else if(this.value ==8){
			return "ELINE";
		}else if(this.value ==9){
			return "PWPROTECT";
		}else if(this.value ==10){
			return "OAMPING";
		}else if(this.value ==11){
			return "ETHLOOP";
		}else if(this.value ==12){
			return "BLACKWHITEMAC";
		}else if(this.value ==13){
			return "PW";
		}else if(this.value ==14){
			return "MSPW";
		}else if(this.value ==15){
			return "TIMESYNC";
		}else if(this.value ==16){
			return "OSPFWH";
		}
		return null;
	}
}
