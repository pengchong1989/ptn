package com.nms.db.enums;

public enum OamTypeEnum {
	AMEP(1), ZMEP(2), MIP(3), LINKOAM(4), MEP(5);

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private OamTypeEnum(int value) {
		this.value = value;
	}

	public static OamTypeEnum from(int value) {
		for (OamTypeEnum oam : OamTypeEnum.values()) {
			if (oam.getValue() == value) {
				return oam;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		if (this.value == 1) {
			return "AMEP";
		} else if (this.value == 2) {
			return "ZMEP";
		} else if (this.value == 3) {
			return "MIP";
		} else if (this.value == 4) {
			return "LINKOAM";
		} else if (this.value == 5) {
			return "MEP";
		}
		return "";
	}
}
