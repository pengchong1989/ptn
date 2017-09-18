package com.nms.drivechenxiao.service.bean.oam;

public class OamMipObject {
	private String megid = "";
	private String lvl = "";
	private String mipid = "";
	private String hwres = "";
	private String aMepId = "";
	private String zMepId = "";

	public String getAMepId() {
		return aMepId;
	}

	public void setAMepId(String mepId) {
		aMepId = mepId;
	}

	public String getZMepId() {
		return zMepId;
	}

	public void setZMepId(String mepId) {
		zMepId = mepId;
	}

	public String getMegid() {
		return megid;
	}

	public void setMegid(String megid) {
		this.megid = megid;
	}

	public String getLvl() {
		return lvl;
	}

	public void setLvl(String lvl) {
		this.lvl = lvl;
	}

	public String getMipid() {
		return mipid;
	}

	public void setMipid(String mipid) {
		this.mipid = mipid;
	}

	public String getHwres() {
		return hwres;
	}

	public void setHwres(String hwres) {
		this.hwres = hwres;
	}

}
