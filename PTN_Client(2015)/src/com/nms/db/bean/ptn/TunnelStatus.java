package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class TunnelStatus extends ViewDataObj{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5666371829266213603L;
	private int tunnelID;
	private String tunnelName;
	private int inPort;
	private int inLable;
	private int outPort;
	private int outLable;
	private int nodeType;
	private String inPortName;
	private String outPortName;
	
	public String getInPortName() {
		return inPortName;
	}

	public void setInPortName(String inPortName) {
		this.inPortName = inPortName;
	}

	public String getOutPortName() {
		return outPortName;
	}

	public void setOutPortName(String outPortName) {
		this.outPortName = outPortName;
	}

	public int getTunnelID() {
		return tunnelID;
	}

	public void setTunnelID(int tunnelID) {
		this.tunnelID = tunnelID;
	}

	public String getTunnelName() {
		return tunnelName;
	}

	public void setTunnelName(String tunnelName) {
		this.tunnelName = tunnelName;
	}

	public int getInPort() {
		return inPort;
	}

	public void setInPort(int inPort) {
		this.inPort = inPort;
	}

	public int getInLable() {
		return inLable;
	}

	public void setInLable(int inLable) {
		this.inLable = inLable;
	}

	public int getOutPort() {
		return outPort;
	}

	public void setOutPort(int outPort) {
		this.outPort = outPort;
	}

	public int getOutLable() {
		return outLable;
	}

	public void setOutLable(int outLable) {
		this.outLable = outLable;
	}

	public int getNodeType() {
		return nodeType;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	@Override
	public void putObjectProperty() {
		this.putClientProperty("tunnelName",getTunnelName());
		this.putClientProperty("nodeType",nodeType(getNodeType()));
		this.putClientProperty("inport",getInPortName());
		this.putClientProperty("inlable",getInLable());
		this.putClientProperty("outport",getOutPortName());
		this.putClientProperty("outlable",getOutLable());
	}
	
	public String nodeType(int type){
		String src = "";
		if(type == 0){
			src = ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE);
		}else if(type == 1){
			src = ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET);
		}else{
			src = ResourceUtil.srcStr(StringKeysLbl.LBL_LSP_XC);
		}
		return src;
	}
}
