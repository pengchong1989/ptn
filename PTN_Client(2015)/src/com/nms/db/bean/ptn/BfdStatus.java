package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;


public class BfdStatus extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8938720721107597590L;
	private int bfdId;
	private int mySid;//本地回话标识符
	private int peerSid;//对端会话标识符
	private int bfdStatus;//bfd状态
	private int bfdSend;//bfd发送包数
	private int bfdRe; //bfd接收包数
	private int bfdReWorkingPeriod; //Bfd 报文接收时间间隔
	private int bfdSeWorkingPeriod; //Bfd 报文发送时间间隔
	private int bfdType; //bfd类型
	private int portId; //端口号
	private String portName; //端口号
	private int lspLabel; //LSP标签
	private int pwLabel; //pw标签
	private int pwId;
	private int lspId;
	private String lspName;
	private String pwName;
	
	public int getBfdId() {
		return bfdId;
	}
	public void setBfdId(int bfdId) {
		this.bfdId = bfdId;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}
	public int getMySid() {
		return mySid;
	}
	public void setMySid(int mySid) {
		this.mySid = mySid;
	}
	public int getPeerSid() {
		return peerSid;
	}
	public void setPeerSid(int peerSid) {
		this.peerSid = peerSid;
	}
	public int getBfdStatus() {
		return bfdStatus;
	}
	public void setBfdStatus(int bfdStatus) {
		this.bfdStatus = bfdStatus;
	}
	public int getBfdSend() {
		return bfdSend;
	}
	public void setBfdSend(int bfdSend) {
		this.bfdSend = bfdSend;
	}
	public int getBfdRe() {
		return bfdRe;
	}
	public void setBfdRe(int bfdRe) {
		this.bfdRe = bfdRe;
	}
	public int getBfdReWorkingPeriod() {
		return bfdReWorkingPeriod;
	}

	public void setBfdReWorkingPeriod(int bfdReWorkingPeriod) {
		this.bfdReWorkingPeriod = bfdReWorkingPeriod;
	}

	public int getBfdSeWorkingPeriod() {
		return bfdSeWorkingPeriod;
	}

	public void setBfdSeWorkingPeriod(int bfdSeWorkingPeriod) {
		this.bfdSeWorkingPeriod = bfdSeWorkingPeriod;
	}

	public int getBfdType() {
		return bfdType;
	}

	public void setBfdType(int bfdType) {
		this.bfdType = bfdType;
	}

	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}

	public int getLspLabel() {
		return lspLabel;
	}

	public void setLspLabel(int lspLabel) {
		this.lspLabel = lspLabel;
	}

	public int getPwLabel() {
		return pwLabel;
	}

	public void setPwLabel(int pwLabel) {
		this.pwLabel = pwLabel;
	}

	public int getPwId() {
		return pwId;
	}

	public void setPwId(int pwId) {
		this.pwId = pwId;
	}

	public int getLspId() {
		return lspId;
	}

	public void setLspId(int lspId) {
		this.lspId = lspId;
	}

	public String getLspName() {
		return lspName;
	}

	public void setLspName(String lspName) {
		this.lspName = lspName;
	}

	public String getPwName() {
		return pwName;
	}

	public void setPwName(String pwName) {
		this.pwName = pwName;
	}

	@Override
	public void putObjectProperty() {
		this.putClientProperty("bfdId",getBfdId());
		this.putClientProperty("mySid",getMySid());
		this.putClientProperty("peerSid",getPeerSid());
		this.putClientProperty("bfdStatus",getStatus(getBfdStatus()));
		this.putClientProperty("bfdSend",getBfdSend());
		this.putClientProperty("bfdRe",getBfdRe());
		this.putClientProperty("bfdReWorkingPeriod",getPeriod(getBfdReWorkingPeriod()));
		this.putClientProperty("bfdSeWorkingPeriod",getPeriod(getBfdSeWorkingPeriod()));
		this.putClientProperty("bfdType",getType(getBfdType()));
		this.putClientProperty("lspLabel",getLspLabel());
		this.putClientProperty("pwLabel",getPwLabel());
		this.putClientProperty("lspId",getLspId());
		this.putClientProperty("lspName",getLspName());
		this.putClientProperty("pwId",getPwId());
		this.putClientProperty("pwName",getPwName());
		this.putClientProperty("portName",getPortName());

	
	}
	
	public String getType(int number){
		String str = "";
		if(number == 0){
			str = "IP";
		}else if(number == 1){
			str = "LSP";
		}else if(number == 2){
			str = "PW+LSP";
		}
		return str;		
	}
	
	public String getStatus(int number){
		String str = "";
		if(number == 0){
			str = "admindown";
		}else if(number == 1){
			str = "down";
		}else if(number == 2){
			str = "init";
		}else if(number == 3){
			str = "up";
		}
		return str;		
	}
	
	public String getPeriod(int number){
		String str = "";
		if(number == 0){
			str = "-";
		}else if(number == 1){
			str = "3.3ms";
		}else if(number == 2){
			str = "10ms";
		}else if(number == 3){
			str = "20ms";
		}else if(number == 4){
			str = "30ms";
		}else if(number == 5){
			str = "40ms";
		}else if(number == 6){
			str = "50ms";
		}else if(number == 7){
			str = "60ms";
		}else if(number == 8){
			str = "70ms";
		}else if(number == 9){
			str = "80ms";
		}else if(number == 10){
			str = "90ms";
		}else if(number == 11){
			str = "100ms";
		}else if(number == 12){
			str = "200ms";
		}else if(number == 13){
			str = "300ms";
		}else if(number == 14){
			str = "400ms";
		}else if(number == 15){
			str = "500ms";
		}else if(number == 16){
			str = "150ms";
		}else if(number == 17){
			str = "250ms";
		}else if(number == 18){
			str = "350ms";
		}else if(number == 19){
			str = "450ms";
		}else if(number == 20){
			str = "1000ms";
		}
		return str;		
	}
		
}
