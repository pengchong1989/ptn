package com.nms.corba.ninterface.enums;
/**
 * 单元盘类型
 * @author dzy
 *
 */
public enum EEquipmentType {
	UNKNOW(0),		//未知
	MAINCONTROLERBOARD(1),	//主控板
	INTERFACEDISH(2),	//接口盘
	FANBOARD(3),	//风扇版
	POWERDISH(4);	//电源盘
	private int value;
	private EEquipmentType(int value) {
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public EEquipmentType from(int value) {
		for(EEquipmentType eProtectionType : EEquipmentType.values()){
			if(value == eProtectionType.value)
				return eProtectionType;
		}
		return null;
	}
	@Override
	public String toString() {
		if(this.value == 0){
			return "UNKNOW";
		}else if(this.value == 1){
			return "MAINCONTROLERPANEL";
		}else if(this.value == 2){
			return "INTERFACEDISH";
		}else if(this.value == 3){
			return "FANPANEL";
		}else if(this.value == 4){
			return "POWERDISH";
		}
		return "";
	}
}
