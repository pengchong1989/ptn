package com.nms.drivechenxiao.service.bean.porteth.nni;

public class NNIObject {
	private String llspexptoclr = "";// LLSP EXP到颜色的映射
	private String llspclrtoexp = "";// 颜色到LLSP EXP的映射
	private String eelspexptocos = "";// EELSP EXP到业务等级和颜色的映射
	private String eelspcostoexp = "";// 业务等级和颜色到EELSP EXP的映射
	private String elspexptocos = "";// ELSP EXP到业务等级的映射
	private String elspcostoexp = "";// 业务等级到ELSP EXP的映射
	private String inbwlimit = "";// 入口带宽限制(单位:kbps,步长为64kpbs)
	private String egbwlimit = "";// 出口带宽限制(单位:kbps,步长为64kpbs )
	private String pvid = "";// NNI接口 VLAN ID
	private String ccnEnableString="";//ccn承载使能
	
	public String toString(){
		StringBuffer sb=new StringBuffer().append(" llspexptoclr=").append(llspexptoclr)
		.append(" ;llspclrtoexp=").append(llspclrtoexp)
		.append(" ;eelspexptocos=").append(eelspexptocos)
		.append(" ;eelspcostoexp=").append(eelspcostoexp)
		.append(" ;elspexptocos=").append(elspexptocos)
		.append(" ;elspcostoexp=").append(elspcostoexp)
		.append(" ;inbwlimit=").append(inbwlimit)
		.append(" ;egbwlimit=").append(egbwlimit)
		.append(" ;pvid=").append(pvid).append(" ;ccnEnableString=").append(ccnEnableString);
		return sb.toString();
	}

	public String getCcnEnableString() {
		return ccnEnableString;
	}

	public void setCcnEnableString(String ccnEnableString) {
		this.ccnEnableString = ccnEnableString;
	}

	public String getLlspexptoclr() {
		return llspexptoclr;
	}

	public void setLlspexptoclr(String llspexptoclr) {
		this.llspexptoclr = llspexptoclr;
	}

	public String getLlspclrtoexp() {
		return llspclrtoexp;
	}

	public void setLlspclrtoexp(String llspclrtoexp) {
		this.llspclrtoexp = llspclrtoexp;
	}

	public String getEelspexptocos() {
		return eelspexptocos;
	}

	public void setEelspexptocos(String eelspexptocos) {
		this.eelspexptocos = eelspexptocos;
	}

	public String getEelspcostoexp() {
		return eelspcostoexp;
	}

	public void setEelspcostoexp(String eelspcostoexp) {
		this.eelspcostoexp = eelspcostoexp;
	}

	public String getElspexptocos() {
		return elspexptocos;
	}

	public void setElspexptocos(String elspexptocos) {
		this.elspexptocos = elspexptocos;
	}

	public String getElspcostoexp() {
		return elspcostoexp;
	}

	public void setElspcostoexp(String elspcostoexp) {
		this.elspcostoexp = elspcostoexp;
	}

	public String getInbwlimit() {
		return inbwlimit;
	}

	public void setInbwlimit(String inbwlimit) {
		this.inbwlimit = inbwlimit;
	}

	public String getEgbwlimit() {
		return egbwlimit;
	}

	public void setEgbwlimit(String egbwlimit) {
		this.egbwlimit = egbwlimit;
	}

	public String getPvid() {
		return pvid;
	}

	public void setPvid(String pvid) {
		this.pvid = pvid;
	}

}
