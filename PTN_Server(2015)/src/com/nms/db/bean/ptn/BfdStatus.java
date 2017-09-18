package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

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
//		this.putClientProperty("portName",getPortName());
//		this.putClientProperty("link",getStatus()==0?"LINK DOWN":"LINK UP");
//		this.putClientProperty("model",getPortModel(getModel()));
//		this.putClientProperty("LoopState",getPortLoopModel(getLoopState()));
//		this.putClientProperty("received",getReceivePackage());
//		this.putClientProperty("send",getLaunchPackage());
	}
	
	private Object getPortLoopModel(int model2) {
		String str = "";
		if(model2 == 0){      
			str = ResourceUtil.srcStr(StringKeysLbl.LBL_PORTLOOPMODAL1);
		}else if(model2 == 1){
			str = ResourceUtil.srcStr(StringKeysLbl.LBL_PORTLOOPMODAL2);
		}else if(model2 == 2){
			str = ResourceUtil.srcStr(StringKeysLbl.LBL_PORTLOOPMODAL3);
		}
		return str;
	}
	public String getPortModel(int number){
		String str = "";
		if(number == 0){
			str = "FE";
		}else if(number == 1){
			str = "GE";
		}else if(number == 2){
			str = "FX";
		}
		return str;
	}
}
