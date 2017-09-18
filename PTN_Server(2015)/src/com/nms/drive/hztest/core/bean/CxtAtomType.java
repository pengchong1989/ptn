package com.nms.drive.hztest.core.bean;

public class CxtAtomType {
	public static String AT_NUM_32 = "AT_NUM_32";
	public static String AT_STRING = "AT_STRING";
	public static String AT_TABLE = "AT_TABLE";

	private String type = "";
	private int cxtInt = 0;
	private String cxtString = "";
	private CxtATTable cxtATTable = null;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCxtInt() {
		return cxtInt;
	}

	public void setCxtInt(int cxtInt) {
		this.cxtInt = cxtInt;
	}

	public String getCxtString() {
		return cxtString;
	}

	public void setCxtString(String cxtString) {
		this.cxtString = cxtString;
	}

	public CxtATTable getCxtATTable() {
		return cxtATTable;
	}

	public void setCxtATTable(CxtATTable cxtATTable) {
		this.cxtATTable = cxtATTable;
	}
}
