package com.nms.db.bean.ptn.oam;

@SuppressWarnings("serial")
public class OamMipInfo extends OamCommonInfo {
	private int mipId;
	private int aMId;
	private int zMId;
	private int tc;
	public int getAMId() {
		return aMId;
	}

	public void setAMId(int id) {
		aMId = id;
	}

	public int getZMId() {
		return zMId;
	}

	public void setZMId(int id) {
		zMId = id;
	}

	public int getMipId() {
		return mipId;
	}

	public void setMipId(int mipId) {
		this.mipId = mipId;
	}

	public int getTc() {
		return tc;
	}

	public void setTc(int tc) {
		this.tc = tc;
	}

}
