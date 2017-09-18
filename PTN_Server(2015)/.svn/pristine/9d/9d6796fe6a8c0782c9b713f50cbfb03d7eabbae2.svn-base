package com.nms.db.enums;

public enum EManufacturer {
	WUHAN(0),CHENXIAO(1);
	private int value;
	
	private EManufacturer(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EManufacturer forms(int value) {
		for(EManufacturer manufacturer : EManufacturer.values()){
			if(value == manufacturer.value)
				return manufacturer;
		}
		return null;
	}
}
