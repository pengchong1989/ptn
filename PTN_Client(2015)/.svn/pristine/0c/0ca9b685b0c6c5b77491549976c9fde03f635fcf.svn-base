package com.nms.db.enums;

/**
 * 性能对象类型
 * @author lp
 *
 */
public enum EPerformanceObjectType {
	NONE(0),SITEINST(1),SLOTINST(2),TUNNEL(3),PW(4),PORT(5),LSP(6);
	private int value;
	
	private EPerformanceObjectType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EPerformanceObjectType forms(int value) {
		for(EPerformanceObjectType serviceType : EPerformanceObjectType.values()){
			if(value == serviceType.value)
				return serviceType;
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(value == 1)
			return "siteInst";
		else if(value == 2) 
			return "slotInst";
		else if(value == 3) 
			return "tunnel";
		else if(value == 4) 
			return "pw";
		else if(value == 5) 
			return "port";
		else if(value == 6) 
			return "lsp";
		else
			return null;
	}
}
