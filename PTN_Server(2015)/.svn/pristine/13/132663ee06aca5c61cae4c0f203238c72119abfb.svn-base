package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class PortStatus extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8938720721107597590L;
	private int portNumber;
	private int status;//link down/link up
	private int model;//模式 FE/GE/FX
	private int receivePackage;//收包数
	private int launchPackage;//发包数
	private String portName;
	private int loopState;//端口环回状态
	
	
	public int getLoopState() {
		return loopState;
	}
	public void setLoopState(int loopState) {
		this.loopState = loopState;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getReceivePackage() {
		return receivePackage;
	}
	public void setReceivePackage(int receivePackage) {
		this.receivePackage = receivePackage;
	}
	public int getLaunchPackage() {
		return launchPackage;
	}
	public void setLaunchPackage(int launchPackage) {
		this.launchPackage = launchPackage;
	}
	@Override
	public void putObjectProperty() {
		this.putClientProperty("portName",getPortName());
		this.putClientProperty("link",getStatus()==0?"LINK DOWN":"LINK UP");
		this.putClientProperty("model",getPortModel(getModel()));
		this.putClientProperty("LoopState",getPortLoopModel(getLoopState()));
		this.putClientProperty("received",getReceivePackage());
		this.putClientProperty("send",getLaunchPackage());
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
