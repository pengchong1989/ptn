package com.nms.corba.ninterface.enums;
/**
 * 保护组类型
 * @author dzy
 *
 */
public enum EClockSourceType {
	TUNNEL(1),MSPPROTECT(2),LOOPPROTECT(3);
	private int value;
	private EClockSourceType(int value) {
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public EClockSourceType from(int value) {
		for(EClockSourceType eProtectionType : EClockSourceType.values()){
			if(value == eProtectionType.value)
				return eProtectionType;
		}
		return null;
	}
	@Override
	public String toString() {
		if(this.value == 1){
			return "TUNNEL";
		}else if(this.value == 2){
			return "MSPPROTECT";
		}else if(this.value == 3){
			return "LOOPPROTECT";
		}
		return "";
	}
}
