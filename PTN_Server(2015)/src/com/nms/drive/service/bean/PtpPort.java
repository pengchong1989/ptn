package com.nms.drive.service.bean;

public class PtpPort {
	
	private int PortNum=0;  //端口号
	private int workModel=0;  //工作模式
	private int PortId=0;    //端口ID
	private int Line=0;  //线路不对称时延属性
	private int LineCpn=0;  //线路不对称时延补偿值
	private int delayMeca=0;  //延时机制
	private int MessageMode=0;  //消息模式
	private int indexId=0;//索引
	
	
	public int getPortNum() {
		return PortNum;
	}
	public void setPortNum(int portNum) {
		PortNum = portNum;
	}
	public int getWorkModel() {
		return workModel;
	}
	public void setWorkModel(int workModel) {
		this.workModel = workModel;
	}
	public int getPortId() {
		return PortId;
	}
	public void setPortId(int portId) {
		PortId = portId;
	}
	public int getLine() {
		return Line;
	}
	public void setLine(int line) {
		Line = line;
	}
	public int getLineCpn() {
		return LineCpn;
	}
	public void setLineCpn(int lineCpn) {
		LineCpn = lineCpn;
	}
	public int getDelayMeca() {
		return delayMeca;
	}
	public void setDelayMeca(int delayMeca) {
		this.delayMeca = delayMeca;
	}
	public int getMessageMode() {
		return MessageMode;
	}
	public void setMessageMode(int messageMode) {
		MessageMode = messageMode;
	}
	
	public int getIndexId() {
		return indexId;
	}
	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}
}
