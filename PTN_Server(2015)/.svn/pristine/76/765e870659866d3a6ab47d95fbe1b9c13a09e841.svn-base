package com.nms.corba.ninterface.enums;
/**
 * 保护组类型
 * @author dzy
 *
 */
public enum EProtectionGroupType {
	PGP_1_PLUS_1(1),PGP_1_FOR_N(2);
	private int value;
	private EProtectionGroupType(int value) {
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public EProtectionGroupType from(int value) {
		for(EProtectionGroupType eProtectionType : EProtectionGroupType.values()){
			if(value == eProtectionType.value)
				return eProtectionType;
		}
		return null;
	}
	@Override
	public String toString() {
		if(this.value == 1){
			return "PGP_1_PLUS_1";
		}else if(this.value == 2){
			return "PGP_1_FOR_N";
		}
		return "";
	}
}
