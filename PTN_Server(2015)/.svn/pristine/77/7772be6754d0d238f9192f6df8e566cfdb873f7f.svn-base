package com.nms.db.enums;

public enum EPortChildAttr {
	NONE(0) , UNIPORTCHILD(1) , NNIPORTCHILD(2);
	private int value;
	
	private EPortChildAttr(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static EPortChildAttr from(int value) {
		for(EPortChildAttr child : EPortChildAttr.values()) {
			if(child.getValue() == value) 
				return child;
		}
		return null;
	}
}
