package com.nms.drivechenxiao.analysis.tool.bean;
/**数据类型枚举
 * **/
public class CxtAtomType {
	public static String AT_NUM_8 = "AT_NUM_8";
	public static String AT_NUM_32 = "AT_NUM_32";
	public static String AT_NUM_64 = "AT_NUM_64";
	public static String AT_NUM_D = "AT_NUM_D";
	public static String AT_STRING = "AT_STRING";
	public static String AT_TABLE = "AT_TABLE";
	public static String AT_BOOL = "AT_BOOL";

	private String type = "";
	private int cxtInt = 0;
	private long cxtLong = 0;
	private double cxtDouble = 0;
	private String cxtString = "";
	private String bool = "";
	private CxtATTable cxtATTable = null;

	public long getCxtLong() {
		return cxtLong;
	}

	public void setCxtLong(long cxtLong) {
		this.cxtLong = cxtLong;
	}

	public double getCxtDouble() {
		return cxtDouble;
	}

	public void setCxtDouble(double cxtDouble) {
		this.cxtDouble = cxtDouble;
	}

	public String getBool() {
		return bool;
	}

	public void setBool(String bool) {
		this.bool = bool;
	}

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
