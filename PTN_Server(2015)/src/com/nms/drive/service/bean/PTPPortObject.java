package com.nms.drive.service.bean;

/**
 * PTP端口配置 
 * TimeSynchronizeObject的子属性
 * 
 * @author hulei
 * 
 */
public class PTPPortObject {
	private int index = 0; // 索引" value="1"
	private int port = 0; // 端口号" value="1"
	private int workPattern = 0; // 工作模式" value="0"
	private int portId = 0; // 端口ID" value="1"
	private int lineAsymmetricTimeDelayAttribute = 0; // 线路不对称时延属性" value="0"
	private int lineAsymmetricTimeDelayValue = 0; // 线路不对称时延补偿值" value="0"
	private int timeDelayMechanism = 0; // 延时机制" value="1"
	private int messagePattern = 0;// 消息模式" value="1"
	private int reserve = 0; // 备用" value="0"

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getWorkPattern() {
		return workPattern;
	}

	public void setWorkPattern(int workPattern) {
		this.workPattern = workPattern;
	}

	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}

	public int getLineAsymmetricTimeDelayAttribute() {
		return lineAsymmetricTimeDelayAttribute;
	}

	public void setLineAsymmetricTimeDelayAttribute(int lineAsymmetricTimeDelayAttribute) {
		this.lineAsymmetricTimeDelayAttribute = lineAsymmetricTimeDelayAttribute;
	}

	public int getLineAsymmetricTimeDelayValue() {
		return lineAsymmetricTimeDelayValue;
	}

	public void setLineAsymmetricTimeDelayValue(int lineAsymmetricTimeDelayValue) {
		this.lineAsymmetricTimeDelayValue = lineAsymmetricTimeDelayValue;
	}

	public int getTimeDelayMechanism() {
		return timeDelayMechanism;
	}

	public void setTimeDelayMechanism(int timeDelayMechanism) {
		this.timeDelayMechanism = timeDelayMechanism;
	}

	public int getMessagePattern() {
		return messagePattern;
	}

	public void setMessagePattern(int messagePattern) {
		this.messagePattern = messagePattern;
	}

	public int getReserve() {
		return reserve;
	}

	public void setReserve(int reserve) {
		this.reserve = reserve;
	}

}
