package com.nms.db.enums;

public enum ECesType {
	SDH(3), SDHPDH(5), PDH(2), PDHSDH(4);

	private int value;

	private ECesType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static ECesType forms(int value) {
		for (ECesType cesType : ECesType.values()) {
			if (value == cesType.value)
				return cesType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 3)
			return "SDH";
		else if (value == 5)
			return "SDH-PDH";
		else if (value == 2)
			return "PDH";
		else if (value == 4)
			return "PDH-SDH";
		else
			return null;
	}
}
