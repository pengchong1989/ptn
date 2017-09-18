package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;

public class VpwsStatus extends ViewDataObj{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8676220508921893757L;
	private int vpwsID;
	private String vpwsName;
	private int bufferID;
	private int uniPortid;
	private String uniPortName;
	private String vpwsType;
	private String vpwsValue1;
	private String vpwsValue2;
	private int nniPortid;
	private String nniPortName;
	private int outTunnelLable;
	private int outPwLable;
	private int inTunnelLable;
	private int inPwlable;
	private int pwId;
	private String pwName;
	public int getVpwsID() {
		return vpwsID;
	}
	public void setVpwsID(int vpwsID) {
		this.vpwsID = vpwsID;
	}
	public String getVpwsName() {
		return vpwsName;
	}
	public void setVpwsName(String vpwsName) {
		this.vpwsName = vpwsName;
	}
	public int getBufferID() {
		return bufferID;
	}
	public void setBufferID(int bufferID) {
		this.bufferID = bufferID;
	}
	public int getUniPortid() {
		return uniPortid;
	}
	public void setUniPortid(int uniPortid) {
		this.uniPortid = uniPortid;
	}
	public String getUniPortName() {
		return uniPortName;
	}
	public void setUniPortName(String uniPortName) {
		this.uniPortName = uniPortName;
	}
	public String getVpwsType() {
		return vpwsType;
	}
	public void setVpwsType(String vpwsType) {
		this.vpwsType = vpwsType;
	}
	public String getVpwsValue1() {
		return vpwsValue1;
	}
	public void setVpwsValue1(String vpwsValue1) {
		this.vpwsValue1 = vpwsValue1;
	}
	public String getVpwsValue2() {
		return vpwsValue2;
	}
	public void setVpwsValue2(String vpwsValue2) {
		this.vpwsValue2 = vpwsValue2;
	}
	public int getNniPortid() {
		return nniPortid;
	}
	public void setNniPortid(int nniPortid) {
		this.nniPortid = nniPortid;
	}
	public String getNniPortName() {
		return nniPortName;
	}
	public void setNniPortName(String nniPortName) {
		this.nniPortName = nniPortName;
	}
	public int getOutTunnelLable() {
		return outTunnelLable;
	}
	public void setOutTunnelLable(int outTunnelLable) {
		this.outTunnelLable = outTunnelLable;
	}
	public int getOutPwLable() {
		return outPwLable;
	}
	public void setOutPwLable(int outPwLable) {
		this.outPwLable = outPwLable;
	}
	public int getInTunnelLable() {
		return inTunnelLable;
	}
	public void setInTunnelLable(int inTunnelLable) {
		this.inTunnelLable = inTunnelLable;
	}
	public int getInPwlable() {
		return inPwlable;
	}
	public void setInPwlable(int inPwlable) {
		this.inPwlable = inPwlable;
	}
	public int getPwId() {
		return pwId;
	}
	public void setPwId(int pwId) {
		this.pwId = pwId;
	}
	public String getPwName() {
		return pwName;
	}
	public void setPwName(String pwName) {
		this.pwName = pwName;
	}
	@Override
	public void putObjectProperty() {
		this.putClientProperty("vpwsName",getVpwsName());
		this.putClientProperty("buffer",getBufferID());
		this.putClientProperty("uni",getUniPortName());
		this.putClientProperty("vpwstype",getVpwsType());
		this.putClientProperty("value1",getVpwsValue1());
		this.putClientProperty("value2",getVpwsValue2());
		this.putClientProperty("nni",getNniPortName());
		this.putClientProperty("outtunnellable",getOutTunnelLable());
		this.putClientProperty("outpwlable",getOutPwLable());
		this.putClientProperty("intunnellable",getInTunnelLable());
		this.putClientProperty("inpwlable",getInPwlable());
		this.putClientProperty("pwname",getPwName());
	}

}
