package com.nms.db.enums;

public enum QosCosLevelEnum {
	BE(0),AF1(1),AF2(2),AF3(3),AF4(4),
	EF(5),CS6(6),CS7(7);
	
	private int value;
	
	private QosCosLevelEnum(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
	public static QosCosLevelEnum from(int value){
		for(QosCosLevelEnum cos : QosCosLevelEnum.values()){
			if( value == cos.getValue()){
				return cos;
			}
		}
		return null;
	}
	
	public static int from(String cos){
		if(cos.equals(QosCosLevelEnum.BE.toString())){
			return 0;
		}else if(cos.equals(QosCosLevelEnum.AF1.toString())){
			return 1;
		}else if(cos.equals(QosCosLevelEnum.AF2.toString())){
			return 2;
		}else if(cos.equals(QosCosLevelEnum.AF3.toString())){
			return 3;
		}else if(cos.equals(QosCosLevelEnum.AF4.toString())){
			return 4;
		}else if(cos.equals(QosCosLevelEnum.EF.toString())){
			return 5;
		}else if(cos.equals(QosCosLevelEnum.CS6.toString())){
			return 6;
		}else {
			return 7;
		}
	}
	
	@Override
	public String toString(){
		if(value == 0){
			return "BE";
		}else if(value == 1){
			return "AF1";
		}else if(value == 2){
			return "AF2";
		}else if(value == 3){
			return "AF3";
		}else if(value == 4){
			return "AF4";
		}else if(value == 5){
			return "EF";
		}else if(value == 6){
			return "CS6";
		}else if(value == 7){
			return "CS7";
		}
		return "";
	}
}
