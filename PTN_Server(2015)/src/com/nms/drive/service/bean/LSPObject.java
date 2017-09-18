package com.nms.drive.service.bean;

public class LSPObject {

	private int lspId = 0;// LSP ID
	private int tunnelType =0;
	private int inSlot = 0;// 入槽位号
	private int inProt = 0;// 入端口号
	private int inLable = 0;// 入标签
	private int inTTL = 255;//入标记TTL
	private int inTC = 0;//入标记TC
	
	private int outSlot = 0;// 出槽位号
	private int outprot = 0;// 出端口号
	private int outLable = 0;// 出标签
	private int outTTL = 255;//入标记TTL
	private int outTC = 0;//入标记TC
	
	private int bandwidthEnable = 0;// 带宽控制使能 (0)/1=(不使能)/使能
	private int cir = 1000;// CIR
	private int pir = 1000;// PIR
	private int cbs = 0;//CBS
	private int pbs = 0;//PBS
	private int cos = 0;//COS:0/1/2/3/4/5/6/7=BE/AF1/AF2/AF3/AF4/EF/CS6/CS7
	private int panelpointid = 0;//目的节点ID:00/01/02…/0xff =无/节点1/节点2……/节点255
	private String sourceMac="00-00-00-00-00-00";
	private String targetMac="00-00-00-00-00-00";
	private int vlanEnable;
	private int outVlanValue;
	private int tpId;
	private String sourceIP = "0.0.0.0";
	private String targetIP = "0.0.0.0";
	
	public String getSourceIP() {
		return sourceIP;
	}

	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	public String getTargetIP() {
		return targetIP;
	}

	public void setTargetIP(String targetIP) {
		this.targetIP = targetIP;
	}

	public int getTunnelType() {
		return tunnelType;
	}

	public void setTunnelType(int tunnelType) {
		this.tunnelType = tunnelType;
	}

	public int getInTTL() {
		return inTTL;
	}

	public void setInTTL(int inTTL) {
		this.inTTL = inTTL;
	}

	public int getInTC() {
		return inTC;
	}

	public void setInTC(int inTC) {
		this.inTC = inTC;
	}

	public int getOutTTL() {
		return outTTL;
	}

	public void setOutTTL(int outTTL) {
		this.outTTL = outTTL;
	}

	public int getOutTC() {
		return outTC;
	}

	public void setOutTC(int outTC) {
		this.outTC = outTC;
	}

	public int getCbs() {
		return cbs;
	}

	public void setCbs(int cbs) {
		this.cbs = cbs;
	}

	public int getPbs() {
		return pbs;
	}

	public void setPbs(int pbs) {
		this.pbs = pbs;
	}

	public int getCos() {
		return cos;
	}

	public void setCos(int cos) {
		this.cos = cos;
	}

	public int getPanelpointid() {
		return panelpointid;
	}

	public void setPanelpointid(int panelpointid) {
		this.panelpointid = panelpointid;
	}

	public int getBandwidthEnable() {
		return bandwidthEnable;
	}

	public void setBandwidthEnable(int bandwidthEnable) {
		this.bandwidthEnable = bandwidthEnable;
	}

	public int getCir() {
		return cir;
	}

	public void setCir(int cir) {
		this.cir = cir;
	}

	public int getPir() {
		return pir;
	}

	public void setPir(int pir) {
		this.pir = pir;
	}

	public int getLspId() {
		return lspId;
	}

	public void setLspId(int lspId) {
		this.lspId = lspId;
	}

	public int getInSlot() {
		return inSlot;
	}

	public void setInSlot(int inSlot) {
		this.inSlot = inSlot;
	}

	public int getInProt() {
		return inProt;
	}

	public void setInProt(int inProt) {
		this.inProt = inProt;
	}

	public int getInLable() {
		return inLable;
	}

	public void setInLable(int inLable) {
		this.inLable = inLable;
	}

	public int getOutSlot() {
		return outSlot;
	}

	public void setOutSlot(int outSlot) {
		this.outSlot = outSlot;
	}

	public int getOutprot() {
		return outprot;
	}

	public void setOutprot(int outprot) {
		this.outprot = outprot;
	}

	public int getOutLable() {
		return outLable;
	}

	public void setOutLable(int outLable) {
		this.outLable = outLable;
	}

	public String getSourceMac() {
		return sourceMac;
	}

	public void setSourceMac(String sourceMac) {
		this.sourceMac = sourceMac;
	}

	public String getTargetMac() {
		return targetMac;
	}

	public void setTargetMac(String targetMac) {
		this.targetMac = targetMac;
	}

	public int getVlanEnable() {
		return vlanEnable;
	}

	public void setVlanEnable(int vlanEnable) {
		this.vlanEnable = vlanEnable;
	}

	public int getOutVlanValue() {
		return outVlanValue;
	}

	public void setOutVlanValue(int outVlanValue) {
		this.outVlanValue = outVlanValue;
	}

	public int getTpId() {
		return tpId;
	}

	public void setTpId(int tpId) {
		this.tpId = tpId;
	}

}
