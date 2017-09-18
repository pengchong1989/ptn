package com.nms.db.bean.ptn.clock;

import com.nms.ui.frame.ViewDataObj;

public class PtpPortinfo   extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id =0;
	private int PortNum=0;  //端口号
	private int workModel=0;  //工作模式
	private int PortId=0;    //端口ID
	private int Line=0;  //线路不对称时延属性
	private int LineCpn=0;  //线路不对称时延补偿值
	private int delayMeca=0;  //延时机制
	private int MessageMode=0;  //消息模式
	private int indexId=0;  //索引
	private int siteId=0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIndexId() {
		return indexId;
	}
	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}
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
	public void setDelayMeca(int elayMeca) {
		this.delayMeca = elayMeca;
	}
	public int getMessageMode() {
		return MessageMode;
	}
	public void setMessageMode(int messageMode) {
		MessageMode = messageMode;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		
		this.getClientProperties().put("IndexId", this.getIndexId());
		
		if(this.getPortNum()==1){
			this.getClientProperties().put("PortNum", "GE1");
		}else if(this.getPortNum()==2){
			this.getClientProperties().put("PortNum", "GE2");
		}else if(this.getPortNum()==3){
			this.getClientProperties().put("PortNum", "GE3");
		}else if(this.getPortNum()==4){
			this.getClientProperties().put("PortNum", "GE4");
		}else if(this.getPortNum()==5){
			this.getClientProperties().put("PortNum", "FE1");
		}else if(this.getPortNum()==6){
			this.getClientProperties().put("PortNum", "FE2");
		}
		
		if(this.getWorkModel()==0){
			this.getClientProperties().put("workMode", "Disable");
		}else if(this.getWorkModel()==1){
			this.getClientProperties().put("workMode", "Auto");
		}else if(this.getWorkModel()==2){
			this.getClientProperties().put("workMode", "Master");
		}else if(this.getWorkModel()==3){
			this.getClientProperties().put("workMode", "Slave");
		}
		
		this.getClientProperties().put("portId", Integer.toHexString(this.getPortId()));
		
		if(this.getLine()==0){
			this.getClientProperties().put("Line", "Delay(M2S)=Delay(S2M)");
		}else if(this.getLine()==1){
			this.getClientProperties().put("Line", "Delay(M2S)>Delay(S2M)");
		}else if(this.getLine()==2){
			this.getClientProperties().put("Line", "Delay(M2S)<Delay(S2M)");
		}
		
		
		this.getClientProperties().put("LineCpn", this.getLineCpn());		
		this.getClientProperties().put("delayMeca", this.getDelayMeca()==1?"E2E":"P2P");		
		this.getClientProperties().put("MessageMode", this.getMessageMode()==0?"one_step":"two_step");
		
	}
	/**
	 * @return the siteId
	 */
	public int getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
}
