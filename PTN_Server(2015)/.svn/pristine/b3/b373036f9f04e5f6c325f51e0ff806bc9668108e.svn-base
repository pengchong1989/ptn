package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;

public class PtpPortStatus extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8938720721107597590L;
	private int indexId;//索引
	private int slotId;//槽位号
	private int portNum;//端口号
	private int portStatus;//端口状态
	private int portClockId;//端口时钟ID
	private int portId;//端口ID
	private int couplePortId;//对偶端口ID
	private int Line;//线路不对称时延属性
	private int LineCpn;  //线路不对称时延补偿值
	private int delayMeca;  //延时机制
	private int MessageMode;  //消息模式

	public int getIndexId() {
		return indexId;
	}

	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	public int getPortStatus() {
		return portStatus;
	}

	public void setPortStatus(int portStatus) {
		this.portStatus = portStatus;
	}

	public int getPortClockId() {
		return portClockId;
	}

	public void setPortClockId(int portClockId) {
		this.portClockId = portClockId;
	}

	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}

	public int getCouplePortId() {
		return couplePortId;
	}

	public void setCouplePortId(int couplePortId) {
		this.couplePortId = couplePortId;
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

	@Override
	public void putObjectProperty() {
		this.putClientProperty("indexId",getIndexId());
		this.putClientProperty("slotId",getSlotIds(getSlotId()));		
		if(getPortNum()==1){
			this.putClientProperty("PortNum", "GE1");
		}else if(this.getPortNum()==2){
			this.putClientProperty("PortNum", "GE2");
		}else if(this.getPortNum()==3){
			this.putClientProperty("PortNum", "GE3");
		}else if(this.getPortNum()==4){
			this.putClientProperty("PortNum", "GE4");
		}else if(this.getPortNum()==5){
			this.putClientProperty("PortNum", "FE1");
		}else if(this.getPortNum()==6){
			this.putClientProperty("PortNum", "FE2");
		}else if(this.getPortNum()==0){
			this.putClientProperty("PortNum", "NULL");
		}
		this.putClientProperty("portStatus",getPortStatuss(getPortStatus()));			
		this.putClientProperty("portId", Integer.toHexString(this.getPortId()));
		this.putClientProperty("portClockId", Integer.toHexString(this.getPortClockId()));
		this.putClientProperty("couplePortId",Integer.toHexString(this.getCouplePortId()));
		if(this.getLine()==0){
			this.putClientProperty("Line", "Delay(M2S)=Delay(S2M)");
		}else if(this.getLine()==1){
			this.putClientProperty("Line", "Delay(M2S)>Delay(S2M)");
		}else if(this.getLine()==2){
			this.putClientProperty("Line", "Delay(M2S)<Delay(S2M)");
		}		
		this.putClientProperty("LineCpn",this.getLineCpn());
		this.putClientProperty("delayMeca", this.getDelayMeca()==1?"E2E":"P2P");		
		this.putClientProperty("MessageMode", this.getMessageMode()==0?"one_step":"two_step");
	}
	
	private String getSlotIds(int slotId) {
		String str = "";
		if(slotId == 255){      
			str = "NUll";
		}else if(slotId == 16){
			str = "SLOT10";
		}else if(slotId == 17){
			str = "SLOT11";
		}else if(slotId == 18){
			str = "SLOT12";
		}else if(slotId == 19){
			str = "SLOT13";
		}else if(slotId == 20){
			str = "SLOT14";
		}else if(slotId == 21){
			str = "SLOT15";
		}else if(slotId == 22){
			str = "SLOT16";
		}else if(slotId == 23){
			str = "SLOT17";
		}else if(slotId == 24){
			str = "SLOT18";
		}else if(slotId == 25){
			str = "SLOT19";
		}
		return str;
	}
	public String getPortStatuss(int portStatus){
		String str = "";
		if(portStatus == 1){
			str = "INITIALIZING";
		}else if(portStatus == 2){
			str = "FAULTY";
		}else if(portStatus == 3){
			str = "DISABLED";
		}else if(portStatus == 4){
			str = "LISTENING";
		}else if(portStatus == 5){
			str = "PRE_MASTER";
		}else if(portStatus == 6){
			str = "MASTER";
		}else if(portStatus == 7){
			str = "PASSIVE";
		}else if(portStatus == 8){
			str = "UNCALIBRATED";
		}else if(portStatus == 9){
			str = "SLAVE";
		}else if(portStatus == 10){
			str = "TEST";
		}
		return str;
	}

	
}
