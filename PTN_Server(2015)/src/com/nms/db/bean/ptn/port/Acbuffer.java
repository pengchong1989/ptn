package com.nms.db.bean.ptn.port;

import java.io.Serializable;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class Acbuffer extends ViewDataObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2785904084102277994L;
	private int id;
	private int acId;
	private int bufferEnable;
	private int vlanId;
	private String sourceMac;
	private String targetMac;
	private int eightIp;
	private String sourceIp;
	private String targetIp;
	private int ipDscp;
	private int model;
	private int cir;
	private int pir;
	private int cm;
	private int cbs;
	private int pbs;
	private int strategy;
	private int phb;
	private int portId;
	private int siteId;
	private int seq;
	private int eir;
	private int ebs;
	
	private String clientVlanIdValue;
	private String clientVlanIdMask;
	private String operatorVlanIdValue;
	private String operatorVlanIdMask;
	private String clientVlanPriorityValue;
	private String clientVlanPriorityMask;
	private String operatorVlanPriorityValue;
	private String operatorVlanPriorityMask;
	private String ethernetTypeValue;
	private String ethernetTypeMask;
	private String sourceMacLabelMask;
	private String sinkMacLabelMask;
	private String iPTypeValue;
	private String iPTypeMask;

	private String sourceIpLabelMask;
	
	private String sinkIpLabelMask;
	private String colorAware;
	private String ethernetType;
	private String qosName;
	private String qosType;
	private String appendBufferName;
	private boolean isCreate = true; 
	private int simpleQosId;
/*	private String sinkIpLabelValue;
	private String sinkMacLabelValue;
	private String sourceMacLabelValue;
	private String sourceIpLabelValue;*/
	private int sourceTcpPortId;///源TCP/UDP端口号
	private int endTcpPortId;//宿TCP/UDP端口号
	private int IPTOS;
	private int portType;
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
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getPortId() {
		return portId;
	}
	public void setPortId(int portId) {
		this.portId = portId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAcId() {
		return acId;
	}
	public void setAcId(int acId) {
		this.acId = acId;
	}
	public int getBufferEnable() {
		return bufferEnable;
	}
	public void setBufferEnable(int bufferEnable) {
		this.bufferEnable = bufferEnable;
	}
	public int getVlanId() {
		return vlanId;
	}
	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
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
	public int getEightIp() {
		return eightIp;
	}
	public void setEightIp(int eightIp) {
		this.eightIp = eightIp;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", this.getId());
			getClientProperties().put("vlanid", this.getVlanId());
			getClientProperties().put("vlanpri", this.getEightIp());
			getClientProperties().put("phb", UiUtil.getCodeById(this.getPhb()).getCodeName());
			getClientProperties().put("cir", this.getCir());
			getClientProperties().put("cbs", this.getCbs());
			getClientProperties().put("colorSence", this.getCm());
			getClientProperties().put("pir", this.getPir());
			getClientProperties().put("pbs", this.getPbs());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	public String getClientVlanIdValue() {
		return clientVlanIdValue;
	}
	public void setClientVlanIdValue(String clientVlanIdValue) {
		this.clientVlanIdValue = clientVlanIdValue;
	}
	public String getClientVlanIdMask() {
		return clientVlanIdMask;
	}
	public void setClientVlanIdMask(String clientVlanIdMask) {
		this.clientVlanIdMask = clientVlanIdMask;
	}
	public String getOperatorVlanIdValue() {
		return operatorVlanIdValue;
	}
	public void setOperatorVlanIdValue(String operatorVlanIdValue) {
		this.operatorVlanIdValue = operatorVlanIdValue;
	}
	public String getOperatorVlanIdMask() {
		return operatorVlanIdMask;
	}
	public void setOperatorVlanIdMask(String operatorVlanIdMask) {
		this.operatorVlanIdMask = operatorVlanIdMask;
	}
	public String getClientVlanPriorityValue() {
		return clientVlanPriorityValue;
	}
	public void setClientVlanPriorityValue(String clientVlanPriorityValue) {
		this.clientVlanPriorityValue = clientVlanPriorityValue;
	}
	public String getClientVlanPriorityMask() {
		return clientVlanPriorityMask;
	}
	public void setClientVlanPriorityMask(String clientVlanPriorityMask) {
		this.clientVlanPriorityMask = clientVlanPriorityMask;
	}
	public String getOperatorVlanPriorityValue() {
		return operatorVlanPriorityValue;
	}
	public void setOperatorVlanPriorityValue(String operatorVlanPriorityValue) {
		this.operatorVlanPriorityValue = operatorVlanPriorityValue;
	}
	public String getOperatorVlanPriorityMask() {
		return operatorVlanPriorityMask;
	}
	public void setOperatorVlanPriorityMask(String operatorVlanPriorityMask) {
		this.operatorVlanPriorityMask = operatorVlanPriorityMask;
	}
	public String getEthernetTypeValue() {
		return ethernetTypeValue;
	}
	public void setEthernetTypeValue(String ethernetTypeValue) {
		this.ethernetTypeValue = ethernetTypeValue;
	}
	public String getEthernetTypeMask() {
		return ethernetTypeMask;
	}
	public void setEthernetTypeMask(String ethernetTypeMask) {
		this.ethernetTypeMask = ethernetTypeMask;
	}

	public String getSourceMacLabelMask() {
		return sourceMacLabelMask;
	}
	public void setSourceMacLabelMask(String sourceMacLabelMask) {
		this.sourceMacLabelMask = sourceMacLabelMask;
	}

	public String getSinkMacLabelMask() {
		return sinkMacLabelMask;
	}
	public void setSinkMacLabelMask(String sinkMacLabelMask) {
		this.sinkMacLabelMask = sinkMacLabelMask;
	}
	public String getiPTypeValue() {
		return iPTypeValue;
	}
	public void setiPTypeValue(String iPTypeValue) {
		this.iPTypeValue = iPTypeValue;
	}
	public String getiPTypeMask() {
		return iPTypeMask;
	}
	public void setiPTypeMask(String iPTypeMask) {
		this.iPTypeMask = iPTypeMask;
	}
	
	public String getSourceIpLabelMask() {
		return sourceIpLabelMask;
	}
	public void setSourceIpLabelMask(String sourceIpLabelMask) {
		this.sourceIpLabelMask = sourceIpLabelMask;
	}

	public String getSinkIpLabelMask() {
		return sinkIpLabelMask;
	}
	public void setSinkIpLabelMask(String sinkIpLabelMask) {
		this.sinkIpLabelMask = sinkIpLabelMask;
	}
	public String getColorAware() {
		return colorAware;
	}
	public void setColorAware(String colorAware) {
		this.colorAware = colorAware;
	}
	public String getEthernetType() {
		return ethernetType;
	}
	public void setEthernetType(String ethernetType) {
		this.ethernetType = ethernetType;
	}
	public String getQosType() {
		return qosType;
	}
	public void setQosType(String qosType) {
		this.qosType = qosType;
	}
	public String getAppendBufferName() {
		return appendBufferName;
	}
	public void setAppendBufferName(String appendBufferName) {
		this.appendBufferName = appendBufferName;
	}
	public boolean isCreate() {
		return isCreate;
	}
	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getEir() {
		return eir;
	}
	public void setEir(int eir) {
		this.eir = eir;
	}
	public int getEbs() {
		return ebs;
	}
	public void setEbs(int ebs) {
		this.ebs = ebs;
	}
	public int getSimpleQosId() {
		return simpleQosId;
	}
	public void setSimpleQosId(int simpleQosId) {
		this.simpleQosId = simpleQosId;
	}
	public String getQosName() {
		return qosName;
	}
	public void setQosName(String qosName) {
		this.qosName = qosName;
	}
}
