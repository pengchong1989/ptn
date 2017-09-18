package com.nms.db.enums;

public enum EDualProtectType {
	BREAKOVER(1), RELEVANCE(2);

	private int value;

	private EDualProtectType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static EDualProtectType forms(int value) {
		for (EDualProtectType cesType : EDualProtectType.values()) {
			if (value == cesType.value)
				return cesType;
		}
		return null;
	}

	@Override
	public String toString() {
		if (value == 1)
			return "BREAKOVER";
		else if (value == 2)
			return "RELEVANCE";
		else
			return null;
	}
}
