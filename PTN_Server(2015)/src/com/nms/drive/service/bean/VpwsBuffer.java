package com.nms.drive.service.bean;

/**
 * 
 * VpwsBuffer VPWS流
 * @author 彭冲
 *
 */
public class VpwsBuffer {
	
	private int vpwsBufferID = 0;
	private int bufferEnable = 0;// 流使能 不使能/(使能)
	private int pwLable = 0;// PW标签
	private int vlanId = 0;// VLAN ID
	private String sourceMac = "00-00-00-00-00-01";// 源MAC地址
	private String targetMac = "00-00-00-00-00-01";// 目的MAC地址
	private int _802_1P = 0;// 802.1P
	private String sourceIP = "";// 源IP地址
	private String targetIP = "";// 目的IP地址
	private int ipDscp = 0;// IP DSCP
	private int model = 0;// 模式 (0)/1/2/=(不使能)/trTCM/Modified trTCM
	private int cir = 0;// CIR
	private int pir = 0;// PIR
	private int cm = 1;// CM
	private int cbs = 0;// CBS
	private int pbs = 0;// PBS
	private int strategy = 0;// 策略(0)/1=(指配PHB)/基于用户优先级到PHB映射
	private int phb = 0;// 指配PHB(0)/1/2/3/4/5/6/7=(BE)/AF1/AF2/AF3/AF4/EF/CS6/CS7
	private int sourceTcpPortId;//源TCP/UDP端口号
	private int endTcpPortId;//宿TCP/UDP端口号
	private int IPTOS;//IP TOS
	private int portType;//端口类型 0/1/2 = 无/TCP/UDP
	public int getPortType() {
		return portType;
	}

	public void setPortType(int portType) {
		this.portType = portType;
	}

	public int getIPTOS() {
		return IPTOS;
	}
	
	public void setIPTOS(int iPTOS) {
		IPTOS = iPTOS;
	}

	public int getSourceTcpPortId() {
		return sourceTcpPortId;
	}

	public void setSourceTcpPortId(int sourceTcpPortId) {
		this.sourceTcpPortId = sourceTcpPortId;
	}

	public int getEndTcpPortId() {
		return endTcpPortId;
	}

	public void setEndTcpPortId(int endTcpPortId) {
		this.endTcpPortId = endTcpPortId;
	}

	public int getVpwsBufferID() {
		return vpwsBufferID;
	}

	public void setVpwsBufferID(int vpwsBufferID) {
		this.vpwsBufferID = vpwsBufferID;
	}

	public int getBufferEnable() {
		return bufferEnable;
	}

	public void setBufferEnable(int bufferEnable) {
		this.bufferEnable = bufferEnable;
	}

	public int getPwLable() {
		return pwLable;
	}

	public void setPwLable(int pwLable) {
		this.pwLable = pwLable;
	}

	public int getVlanId() {
		return vlanId;
	}

	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}

	public int get_802_1P() {
		return _802_1P;
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

	public void set_802_1P(int _802_1p) {
		_802_1P = _802_1p;
	}

	public int getIpDscp() {
		return ipDscp;
	}

	public void setIpDscp(int ipDscp) {
		this.ipDscp = ipDscp;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
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

	public int getCm() {
		return cm;
	}

	public void setCm(int cm) {
		this.cm = cm;
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

	public int getStrategy() {
		return strategy;
	}

	public void setStrategy(int strategy) {
		this.strategy = strategy;
	}

	public int getPhb() {
		return phb;
	}

	public void setPhb(int phb) {
		this.phb = phb;
	}

}
