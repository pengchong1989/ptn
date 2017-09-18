package com.nms.drivechenxiao.service.bean.oam;

public class OamObject {

	private String megid = "";// megid **
	private String lvl = "";// meg等级 **
	private String hwres = "";//	
	private String mepid = "";// 本端**
	private String peer = "";// 对端mepid **
	private String enlm = "";// 是否使能lm
	private String endm = "";// 是否使能dm
	private String cvintvl = "";// cv的周期值 ** 3.33=1
	private String lpbtimeout = "";// 环回超时值 **
	private String iflck = "";// 是否锁定 **
	private String encsf = ""; // **
	
	public String toString(){
		StringBuffer sb=new StringBuffer().append(" megid=").append(megid)
		.append(" ;lvl=").append(lvl).append(" ;hwres=").append(hwres)
		.append(" ;mepid=").append(mepid).append(" ;peer=").append(peer)
		.append(" ;enlm=").append(enlm).append(" ;endm=").append(endm)
		.append(" ;cvintvl=").append(cvintvl).append(" ;lpbtimeout=").append(lpbtimeout)
		.append(" ;iflck=").append(iflck).append(" ;encsf=").append(encsf);
		return sb.toString();
	}
	public String getEncsf() {
		return encsf;
	}

	public void setEncsf(String encsf) {
		this.encsf = encsf;
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

	public String getHwres() {
		return hwres;
	}

	public void setHwres(String hwres) {
		this.hwres = hwres;
	}

	public String getMepid() {
		return mepid;
	}

	public void setMepid(String mepid) {
		this.mepid = mepid;
	}

	public String getPeer() {
		return peer;
	}

	public void setPeer(String peer) {
		this.peer = peer;
	}

	public String getEnlm() {
		return enlm;
	}

	public void setEnlm(String enlm) {
		this.enlm = enlm;
	}

	public String getEndm() {
		return endm;
	}

	public void setEndm(String endm) {
		this.endm = endm;
	}

	public String getCvintvl() {
		return cvintvl;
	}

	public void setCvintvl(String cvintvl) {
		this.cvintvl = cvintvl;
	}

	public String getLpbtimeout() {
		return lpbtimeout;
	}

	public void setLpbtimeout(String lpbtimeout) {
		this.lpbtimeout = lpbtimeout;
	}

	public String getIflck() {
		return iflck;
	}

	public void setIflck(String iflck) {
		this.iflck = iflck;
	}

}
