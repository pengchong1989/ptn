package com.nms.drivechenxiao.analysis.tool.bean;
/**数据结构,由 命令,值结构构成
 * **/
public class CxtOpItem {

	private int op = 0;
	private CxtAtomType cxtAtomType1 = null;
	private CxtAtomType cxtAtomType2 = null;

	public int getOp() {
		return op;
	}

	public void setOp(int op) {
		this.op = op;
	}

	public CxtAtomType getCxtAtomType1() {
		return cxtAtomType1;
	}

	public void setCxtAtomType1(CxtAtomType cxtAtomType1) {
		this.cxtAtomType1 = cxtAtomType1;
	}

	public CxtAtomType getCxtAtomType2() {
		return cxtAtomType2;
	}

	public void setCxtAtomType2(CxtAtomType cxtAtomType2) {
		this.cxtAtomType2 = cxtAtomType2;
	}

}
