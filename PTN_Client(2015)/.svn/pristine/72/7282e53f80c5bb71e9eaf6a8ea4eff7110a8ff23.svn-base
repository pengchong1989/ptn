package com.nms.db.enums;

public enum EMappingColorEnum {
	GREEN(1),YELLOW(2);
	private int value;
	
	private EMappingColorEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	public static EMappingColorEnum from(int value){
		for(EMappingColorEnum type : EMappingColorEnum.values()){
			if(value == type.getValue()){
				return type;
			}
		}
		return null;
	}
	
	public static int from(String value){
		if(value.equals(EMappingColorEnum.GREEN.toString())){
			return 1;
		}else if(value.equals(EMappingColorEnum.YELLOW.toString())){
			return 2;
		}else {
			return 3;
		}
	}
	@Override
	public String toString(){
		if(value == 1){
			return "GREEN";
		}else if(value == 2){
			return "YELLOW";
		}else{
			return "";
		}
		
	}
}
