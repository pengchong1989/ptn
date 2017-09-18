package com.nms.db.enums;

public enum ERootStatus {
	SYSTEMMODU(1),TOGOLOGYMODU(2),DEPLOYMODU(3),COREMODU(4),ALARMMODU(5)
	,PROFORMANCEMODU(6),COUNTMODU(7),SATYMODU(8),SYSTEMmANAGE(9),SYSTEMSELECT(10)
	,TOPOLOGYSMANAGE(11),TOPOLOGYSELECT(12),DEPLOYMANAGE(13),DEPLOYSELECT(14),
	COREMANAGE(16),CORESELECT(16),ALARMMANAGE(17),ALARMSELECT(18),PROFORMANAGE(19)
	,PROFORSELECT(20),COUNTmANAGE(21),COUNTSELECT(22);
	private int value;
	private ERootStatus(int value) {
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public ERootStatus from(int value) {
		for(ERootStatus rootType : ERootStatus.values()){
			if(value == rootType.value)
				return rootType;
		}
		return null;
	}
	@Override
	public String toString() {
		return "";
	}
}
