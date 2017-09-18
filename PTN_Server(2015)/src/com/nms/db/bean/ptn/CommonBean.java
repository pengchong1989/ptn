package com.nms.db.bean.ptn;

import java.io.Serializable;
import com.nms.ui.frame.ViewDataObj;


public class CommonBean extends ViewDataObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1318487589378511002L;
	private String macAdd;
	private String portName;
	private String clockPortName;
	
	//拓扑自动发现用

	private String topuSiteName;
	private String topuPortName;
	private String topuPortType;
	private int topuIsOccupy;
	private String topuMaxFrameSize="1518";//最大帧长=武汉MTU
	
	//QUEUE
	private String qosqueAsiteName;
	private String qosqueAType;
	private String qosqueZsiteName;
	private String qosqueZType;



	public String getQosqueZType() {
		return qosqueZType;
	}

	public void setQosqueZType(String qosqueZType) {
		this.qosqueZType = qosqueZType;
	}

	public String getQosqueZsiteName() {
		return qosqueZsiteName;
	}

	public void setQosqueZsiteName(String qosqueZsiteName) {
		this.qosqueZsiteName = qosqueZsiteName;
	}

	public String getQosqueAType() {
		return qosqueAType;
	}

	public void setQosqueAType(String qosqueAType) {
		this.qosqueAType = qosqueAType;
	}

	public String getQosqueAsiteName() {
		return qosqueAsiteName;
	}

	public void setQosqueAsiteName(String qosqueAsiteName) {
		this.qosqueAsiteName = qosqueAsiteName;
	}

	public int getTopuIsOccupy() {
		return topuIsOccupy;
	}

	public void setTopuIsOccupy(int topuIsOccupy) {
		this.topuIsOccupy = topuIsOccupy;
	}

	public String getTopuSiteName() {
		return topuSiteName;
	}

	public void setTopuSiteName(String topuSiteName) {
		this.topuSiteName = topuSiteName;
	}

	public String getTopuMaxFrameSize() {
		return topuMaxFrameSize;
	}

	public void setTopuMaxFrameSize(String topuMaxFrameSize) {
		this.topuMaxFrameSize = topuMaxFrameSize;
	}

	public String getTopuPortType() {
		return topuPortType;
	}

	public void setTopuPortType(String topuPortType) {
		this.topuPortType = topuPortType;
	}

	public String getTopuPortName() {
		return topuPortName;
	}

	public void setTopuPortName(String topuPortName) {
		this.topuPortName = topuPortName;
	}

	

	public String getClockPortName() {
		return clockPortName;
	}

	public void setClockPortName(String clockPortName) {
		this.clockPortName = clockPortName;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}
	
	public String getMacAdd() {
		return macAdd;
	}

	public void setMacAdd(String macAdd) {
		this.macAdd = macAdd;
	}

	@Override
	public void putObjectProperty() {
		// TODO Auto-generated method stub
		
	}







}
