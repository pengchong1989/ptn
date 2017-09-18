package com.nms.db.enums;

public enum QosTemplateTypeEnum {
	LLSP(1),ELSP(2),EXP(3),VLANPRI_COLOR(4),COS_VLANPRI(5),YELLOW(6),GREEN(7),COLORMODEL(8);
	private int value;
	
	private QosTemplateTypeEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	public static QosTemplateTypeEnum from(int value){
		for(QosTemplateTypeEnum type : QosTemplateTypeEnum.values()){
			if(value == type.getValue()){
				return type;
			}
		}
		return null;
	}
	
	public static int from(String value){
		if(value.equals(QosTemplateTypeEnum.LLSP.toString())){
			return 1;
		}else if(value.equals(QosTemplateTypeEnum.ELSP.toString())){
			return 2;
		}else if(value.equals(QosTemplateTypeEnum.EXP.toString())){
			return 3;
		}else if(value.equals(QosTemplateTypeEnum.VLANPRI_COLOR.toString())){
			return 4;
		}else if(value.equals(QosTemplateTypeEnum.COS_VLANPRI.toString())){
			return 5;
		}else if(value.equals(QosTemplateTypeEnum.YELLOW.toString())){
			return 6;
		}else if(value.equals(QosTemplateTypeEnum.GREEN.toString())){
			return 7;
		}else if(value.equals(QosTemplateTypeEnum.COLORMODEL.toString())){
			return 8;
		}else {
			return 9;
		}
	}
	@Override
	public String toString(){
		if(value == 1){
			return "LLSP";
		}else if(value == 2) {
			return "ELSP";
		}else if(value == 3){
			return "EXP";
		}else if(value == 4) {
			return "VLANPRI_COLOR";
		}else if(value == 5) {
			return "COS_VLANPRI";
		}else if(value == 6) {
			return "YELLOW";
		}else if(value == 7) {
			return "GREEN";
		}else if(value == 8) {
			return "COLORMODEL";
		}else{
			return "";
		}
		
	}
}
